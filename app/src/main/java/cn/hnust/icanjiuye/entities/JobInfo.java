package cn.hnust.icanjiuye.entities;

/**
 * Created by tjouyang on 2017/5/21.
 * 职业资料
 */

public class JobInfo {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "title = " + title +
                "content = " + content;
    }

    //    public String getJob() {
//        return job;
//    }
//
//    public void setJob(String job) {
//        this.job = job;
//    }
//
//    public String getSalary() {
//        return salary;
//    }
//
//    public void setSalary(String salary) {
//        this.salary = salary;
//    }
//
//    public String getPlace() {
//        return place;
//    }
//
//    public void setPlace(String place) {
//        this.place = place;
//    }
//
//    public String getRequirement() {
//        return requirement;
//    }
//
//    public void setRequirement(String requirement) {
//        this.requirement = requirement;
//    }

//    @Override
//    public String toString() {
//        return "title " + title +
//                "job " + job +
//                "salary " + salary +
//                "place " + place +
//                "requirement " + requirement
//
//                ;
//    }
}
