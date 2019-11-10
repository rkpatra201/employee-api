package com.spring.mock.utils;

import com.spring.entity.Employee;
import com.spring.model.Department;
import com.spring.model.Gender;
import com.spring.utils.DateConverterUtils;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestUtils {
    public static List<Employee> getMockEmployees()
    {
        List<Employee> employeeList = new ArrayList<>();
        Employee e1 = new Employee();
        e1.setDateOfBirth(DateConverterUtils.toDate("24-08-1996"));
        e1.setFirstName("F1");
        e1.setLastName("L1");
        e1.setDepartment(Department.FINANCE);
        e1.setGender(Gender.MALE);

        Employee e2 = new Employee();
        e2.setDateOfBirth(DateConverterUtils.toDate("24-08-1996"));
        e2.setFirstName("F2");
        e2.setLastName("L2");
        e2.setDepartment(Department.HR);
        e2.setGender(Gender.FEMALE);

        employeeList.add(e1);
        employeeList.add(e2);

        Collections.sort(employeeList, (o1, o2) -> {
            return o1.getFirstName().compareTo(o2.getFirstName());
        });
        return employeeList;
    }
    public static void compareListOrderByName(List<Employee> list1, List<Employee> list2)
    {
        if(list1==null || list2==null &(list1.size()!=list2.size()))
        {
            throw new RuntimeException("cannot compare two lists");
        }
        for(int i=0;i<list1.size();i++)
        {
            Employee e1=list1.get(i);
            Employee e2=list2.get(i);
            Assertions.assertEquals(e1.getFirstName(),e2.getFirstName());
        }
    }
}
