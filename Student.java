/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ei;

/**
 *
 * @author keerthana
 */
public class Student {
    
    
    private String ID;
    private String name;
    private Classroom classroom;

    public Student(String ID, String name, Classroom classroom) {
        this.ID = ID;
        this.name = name;
        this.classroom = classroom;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Classroom getClassroom() {
        return classroom;
    }
    }
