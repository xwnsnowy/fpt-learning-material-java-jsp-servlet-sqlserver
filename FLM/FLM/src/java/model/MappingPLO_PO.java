/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class MappingPLO_PO {
    private int po,plo,curriculumid;

    public MappingPLO_PO() {
    }

    public MappingPLO_PO(int po, int plo, int curriculumid) {
        this.po = po;
        this.plo = plo;
        this.curriculumid = curriculumid;
    }

    public int getPo() {
        return po;
    }

    public void setPo(int po) {
        this.po = po;
    }

    public int getPlo() {
        return plo;
    }

    public void setPlo(int plo) {
        this.plo = plo;
    }

    public int getCurriculumid() {
        return curriculumid;
    }

    public void setCurriculumid(int curriculumid) {
        this.curriculumid = curriculumid;
    }
    
}
