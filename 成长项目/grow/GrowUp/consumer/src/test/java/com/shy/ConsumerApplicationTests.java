package com.shy;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shy.model.*;
import com.shy.service.*;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@SpringBootTest
class ConsumerApplicationTests {

    @Reference(interfaceClass = UserService.class,version = "1.0.0",check = false,timeout = 15000)
    private UserService usersService;

    @Reference(interfaceClass = DiaryService.class,version = "1.0.0",check = false,timeout = 15000)
    private DiaryService diaryService;

    @Reference(interfaceClass = PersonalService.class,version = "1.0.0",check = false,timeout = 15000)
    private PersonalService personalService;

    @Reference(interfaceClass = UpidService.class,version = "1.0.0",check = false,timeout = 15000)
    private UpidService upidService;

    @Reference(interfaceClass = WorkService.class,version = "1.0.0",check = false,timeout = 15000)
    private WorkService workService;

    @Reference(interfaceClass = UwService.class,version = "1.0.0",check = false,timeout = 15000)
    private UwService uwService;

    @Reference(interfaceClass = PlanService.class,version = "1.0.0",check = false,timeout = 15000)
    private PlanService planService;

    @Reference(interfaceClass = UplanService.class,version = "1.0.0",check = false,timeout = 15000)
    private UplanService uplanService;

    @Reference(interfaceClass = PersonalDiaryService.class,version = "1.0.0",check = false,timeout = 15000)
    private PersonalDiaryService personalDiaryService;

    @Reference(interfaceClass = UdService.class,version = "1.0.0",check = false,timeout = 15000)
    private UdService udService;

    @Reference(interfaceClass = TargetService.class,version = "1.0.0",check = false,timeout = 15000)
    private TargetService targetService;

    @Reference(interfaceClass = UtService.class,version = "1.0.0",check = false,timeout = 15000)
    private UtService utService;

    @Reference(interfaceClass = PropertyService.class,version = "1.0.0",check = false,timeout = 15000)
    private PropertyService propertyService;

    @Reference(interfaceClass = UpropertyService.class,version = "1.0.0",check = false,timeout = 15000)
    private UpropertyService upropertyService;

    @Test
    void contextLoads() {





//        根据用户，更日期和时间
//        List<Work>  works = workService.select("shy");
//        for (int i = 0; i < works.size(); i++) {
//            Work work = works.get(i);
//            System.out.println(work);
//            if (work.getId() == 9){
//                work.setWorkTime("20");
//                work.setId(9);
//                work.setWorkTask("加油");
//                workService.upData(work);
//            }else
//                System.out.println("输入有误");
//        }







//        根据日期查询计划
//        List<Plan> plans = planService.selectPlan("shy");
//        List<Plan> plans1 = new ArrayList<>();
//        for (int i = 0; i < plans.size(); i++) {
//            if (plans.get(i).getPlanDate().equals("2021-2-2")){
//                plans1.add(plans.get(i));
//            }
//        }
//        System.out.println(plans1);

//        更改计划
//        List<Plan>  plans = planService.selectPlan("shy");
//        for (int i = 0; i < plans.size(); i++) {
//            Plan plan = plans.get(i);
//            if (plan.getId() == 5){
//                plan.setId(5);
//                plan.setPlanDate("20");
//                plan.setDayTime("5");
//                plan.setPlanName("shy");
//                plan.setPlanText("睡觉");
//                plan.setPlanStatus("0");
//                planService.upData(plan);
//            }else
//                System.out.println("输入有误");
//        }















//        根据日期查询
//        List<PersonalDiary> personalDiaries = personalDiaryService.select("shy");
//        for (int i = 0; i < personalDiaries.size(); i++) {
//            if (personalDiaries.get(i).getDiaryDate().equals("2021-2-2")) {
//                System.out.println(personalDiaries.get(i));
//            }
//        }



//        根据用户更新日记
//        List<PersonalDiary> personalDiaries = personalDiaryService.select("shy");
//        for (int i = 0; i < personalDiaries.size(); i++) {
//            PersonalDiary personalDiary = personalDiaries.get(i);
//            if (personalDiary.getId() == 3){
//                personalDiary.setId(3);
//                personalDiary.setDiaryMood("恩");
//                personalDiary.setDiaryDate("20");
//                personalDiary.setDiaryName("zph");
//                personalDiary.setDiaryText("我爱你");
//                personalDiary.setDiaryFile("E:\\2.png");
//                personalDiaryService.upData(personalDiary);
//            }
//        }








//        根据日期查询
//        List<Target> targets = targetService.select("shy");
//        for (int i = 0; i < targets.size(); i++) {
//            if (targets.get(i).getTarStart().equals("2021-2-2")) {
//                System.out.println(targets.get(i));
//            }
//        }



//        根据用户更改目标
//        List<Target> targets = targetService.select("shy");
//        for (int i = 0; i < targets.size(); i++) {
//            if (targets.get(i).getId() == 4) {
//                targets.get(i).setId(4);
//                targets.get(i).setTarContent("yes");
//                targets.get(i).setTarStart("20");
//                targets.get(i).setTarOver("22");
//                targets.get(i).setTarName("shy");
//                targetService.upData(targets.get(i));
//            }
//        }









//        根据个人日期查看收支
//        List<Property> properties = propertyService.select("shy");
//        for (int i = 0; i < properties.size(); i++) {
//            if (properties.get(i).getPropertyDate().equals("2021-2-2")) {
//                System.out.println(properties.get(i));
//            }
//        }


//        根据用户更改收支信息
//        List<Property> properties = propertyService.select("shy");
//        for (int i = 0; i < properties.size(); i++) {
//            if (properties.get(i).getId().equals(2)) {
//                properties.get(i).setPropertyDate("20");
//                properties.get(i).setPropertyType("1");
//                properties.get(i).setIncomeExpenditure("2");
//                properties.get(i).setPropertyMoney("270");
//                properties.get(i).setPropertyRemarks("照片我不后悔");
//                properties.get(i).setId(2);
//                propertyService.upDate(properties.get(i));
//            }
//        }





    }
}
