package root.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAttributes {
    private String age;
    private String menopause;
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getMenopause() {
        return menopause;
    }
    public void setMenopause(String menopause) {
        this.menopause = menopause;
    }
    public String getTumor_size() {
        return tumor_size;
    }
    public void setTumor_size(String tumor_size) {
        this.tumor_size = tumor_size;
    }
    public int getInv_nodes() {
        return inv_nodes;
    }
    public void setInv_nodes(int inv_nodes) {
        this.inv_nodes = inv_nodes;
    }
    public String getNode_caps() {
        return node_caps;
    }
    public void setNode_caps(String node_caps) {
        this.node_caps = node_caps;
    }
    public String getDeg_malig() {
        return deg_malig;
    }
    public void setDeg_malig(String deg_malig) {
        this.deg_malig = deg_malig;
    }
    public String getBreast() {
        return breast;
    }
    public void setBreast(String breast) {
        this.breast = breast;
    }
    public String getBreast_quad() {
        return breast_quad;
    }
    public void setBreast_quad(String breat_quad) {
        this.breast_quad = breat_quad;
    }
    public String getIrradiat() {
        return irradiat;
    }
    public void setIrradiat(String irradiat) {
        this.irradiat = irradiat;
    }
    public String getClassResult() {
        return classResult;
    }
    public void setClassResult(String classResult) {
        this.classResult = classResult;
    }
    private String tumor_size;
    private int inv_nodes;
    private String node_caps;
    private String deg_malig;
    private String breast;
    private String breast_quad;
    private String irradiat;
    private String classResult;
}









