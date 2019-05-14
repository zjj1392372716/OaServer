package com.meils.oa.controller;


import com.meils.oa.biz.CourseBiz;
import com.meils.oa.biz.SCBiz;
import com.meils.oa.biz.ScoreBiz;
import com.meils.oa.entity.Course;
import com.meils.oa.entity.SC;
import com.meils.oa.entity.Score;
import com.meils.oa.global.JsonResponse;
import com.meils.oa.global.ListContainer;
import com.meils.oa.global.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("ScoreController")
@RequestMapping("/score")
public class ScoreController {


    @Autowired
    private ScoreBiz scoreBiz;
    @Autowired
    private RedisUtils redisUtils;  //记得注入
    @Autowired
    private CourseBiz courseBiz;


    /**
     * 检查token是否有效
     * @param token
     * @return
     */
    public boolean checkRedis(String token) {
        // 判断token是否有效
        boolean exists = redisUtils.exists(token);

        if(!exists) {
            return false;
        } else {
            return true;
        }

    }


    /**
     * 查询成绩
     * @return
     */
    @RequestMapping(value = "/getScoreList", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getScoreList(
            @RequestParam(value="token", required = true) String  token,
            @RequestParam(value="tId", required = true) Integer  tId,
            @RequestParam(value="page", required = true) Integer page,
            @RequestParam(value="pageSize", required = false) Integer pageSize) {
        if(pageSize == null) {
            pageSize = 10;
        }
        if(page == null) {
            page = 1;
        }
        if("".equals(token) || token == null) {
            System.out.println("未传入token");
            return new JsonResponse(-1, "未传入token");
        }

        boolean exists = this.checkRedis(token);

        if(!exists) {
            return new JsonResponse(-1, "身份验证失败");
        } else {
            System.out.println("session_token is ok");
        }

        try{
            List<Course> ls = courseBiz.findByTId(tId);
            Integer[] idList = new Integer[ls.size()];
            for( int i = 0 ; i < ls.size() ; i++) {
                idList[i] = ls.get(i).getCourseId();
            }
            List<Score> l = scoreBiz.getScoreList(idList, page, pageSize);
            ListContainer list = new ListContainer<Score>(l);
            int nums = scoreBiz.gettotal(idList);
            list.setPage(page);
            list.setPageSize(pageSize);
            int pages = (int)Math.ceil((double)nums / pageSize);
            list.setTotalpages(pages);
            list.setTotals(nums);
            return new JsonResponse<ListContainer>(1, "获取成功", list);


        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }

    /**
     * 录入成绩
     * @return
     */
    @RequestMapping(value = "/updateScore", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getSelectStu(Score score) {

        String token = score.getToken();
        if("".equals(token) || token == null) {
            System.out.println("未传入token");
            return new JsonResponse(-1, "未传入token");
        }

        boolean exists = this.checkRedis(token);

        if(!exists) {
            return new JsonResponse(-1, "身份验证失败");
        } else {
            System.out.println("session_token is ok");
        }

        try{
            scoreBiz.update(score);
            return new JsonResponse<ListContainer>(1, "录入成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "录入失败");
        }
    }

    /**
     * 查询学生的成绩
     * @return
     */
    @RequestMapping(value = "/getScoreStu", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getSelectStu(
            String token,
            Integer sId,
            @RequestParam(value="page", required = true) Integer page,
            @RequestParam(value="pageSize", required = false) Integer pageSize) {
        if(pageSize == null) {
            pageSize = 10;
        }
        if(page == null) {
            page = 1;
        }
        if("".equals(token) || token == null) {
            System.out.println("未传入token");
            return new JsonResponse(-1, "未传入token");
        }

        boolean exists = this.checkRedis(token);

        if(!exists) {
            return new JsonResponse(-1, "身份验证失败");
        } else {
            System.out.println("session_token is ok");
        }

        try{

            List<Score> l = scoreBiz.selectList1(sId, page, pageSize);
            ListContainer list = new ListContainer<Score>(l);
            int nums = scoreBiz.gettotal1(sId);
            list.setPage(page);
            list.setPageSize(pageSize);
            int pages = (int)Math.ceil((double)nums / pageSize);
            list.setTotalpages(pages);
            list.setTotals(nums);
            return new JsonResponse<ListContainer>(1, "获取成功", list);


        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }


}
