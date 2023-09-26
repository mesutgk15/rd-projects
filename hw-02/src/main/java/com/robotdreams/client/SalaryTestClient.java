package com.robotdreams.client;

import com.robotdreams.utils.*;
import com.robotdreams.model.*;
import jakarta.persistence.EntityManager;


public class SalaryTestClient {

    public static void main(String[] args) {
        EntityManager em =  EntityManagerUtils.getEntityManager("mysqlPU");

        //Set rate and hour for VisitingResearchers
        em.find(VisitingResearcher.class, 1).setHourlyRate(100);
        em.find(VisitingResearcher.class, 1).setWorkingHours(5);

        System.out.println(em.find(Instructor.class, 1).calculateSalary());
        System.out.println(em.find(Instructor.class, 2).calculateSalary());

    }
}
