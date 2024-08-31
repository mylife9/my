package com.ruoyi.order.pojo;/**
 * @program: ruoyi-car
 * @author: xlk
 * @description:
 * @create: 2024-08-22 20:08
 */

/**
 * @author : 成长
 * @date : 2024-08-22 20:08
 * @description :
 *
 **/
public class Score {
    private Integer id;
    private String score_name;
    private Double score_value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScore_name() {
        return score_name;
    }

    public void setScore_name(String score_name) {
        this.score_name = score_name;
    }

    public Double getScore_value() {
        return score_value;
    }

    public void setScore_value(Double score_value) {
        this.score_value = score_value;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", score_name='" + score_name + '\'' +
                ", score_value=" + score_value +
                '}';
    }
}
