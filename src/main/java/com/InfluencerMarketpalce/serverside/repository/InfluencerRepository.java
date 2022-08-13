/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Influencer;
import com.InfluencerMarketpalce.serverside.model.response.ProfileDTO;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {


    @Query(value = "SELECT * FROM TB_INFLUENCER WHERE JOB_ID != 'A'", nativeQuery = true)
    List<Influencer> findAllExceptAdmin();

    @Query(value = "SELECT * FROM TB_INFLUENCER ORDER BY RATE DESC", nativeQuery = true)
    List<Influencer> findAllSortByRate();
    
    @Query(value = "SELECT COUNT(*) FROM TB_INFLUENCER WHERE EMAIL = ?1", nativeQuery = true)
    Long findEmail(String email);

    @Query(value = "SELECT TB_INFLUENCER.EMPLOYEE_ID, EMAIL, FULLNAME, JOB_ID FROM TB_INFLUENCER JOIN employee_class \n"
            + "on employee_class.employee_id = tb_employee.employee_id\n"
            + "where employee_class.class_id =?1 AND job_id = 'P'", nativeQuery = true)
    List<Influencer> findEmployeeByClassId(String id);

    @Query(value = "SELECT tb_employee.employee_id, email, fullname, job_id FROM `tb_employee` \n"
            + "left join employee_class on employee_class.employee_id = tb_employee.employee_id\n"
            + "WHERE job_id = 'P' and employee_class.class_id is null", nativeQuery = true)
    List<Influencer> findAllEmployeeWithNoClass();

    @Query(value = "SELECT * FROM `tb_employee` \n"
            + "WHERE job_id = 'T'", nativeQuery = true)
    List<Influencer> findAllTrainer();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM EMPLOYEE_CLASS WHERE employee_id = :id AND class_id = :classId",
            nativeQuery = true)
    void removeEmployeeFromClass(@Param("id") Long employeeId, @Param("classId") String classId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM TRAINER_SUBJECT WHERE trainer_subject_id = :id",
            nativeQuery = true)
    void removeTrainerFromClass(@Param("id") Long trainerSubjectId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO employee_class (employee_id, class_id) VALUES (:employeeId, :classId)",
            nativeQuery = true)
    void insertEmployeeToClass(@Param("employeeId") Long employeeId, @Param("classId") String classId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO trainer_subject (employee_id, subject_id, class_id) VALUES ( :employeeId , :subjectId , :classId )",
            nativeQuery = true)
    void insertTrainerToSubject(@Param("employeeId") Long employeeId,
            @Param("subjectId") Long subjectId,
            @Param("classId") String classId);

    @Query(value = "select tb_user.employee_id as \"idEmployee\", tb_role.role_name as \"roleName\", tb_user.username as \"username\" from tb_user\n"
            + "JOIN user_role ON user_role.employee_id = tb_user.employee_id\n"
            + "JOIN tb_role on user_role.role_id = tb_role.role_id\n"
            + "where tb_user.employee_id =?1", nativeQuery = true)
    ProfileDTO getProfileInfo(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TB_USER SET password = :password where username = :username",
            nativeQuery = true)
    void changePassword(@Param("password") String password,
            @Param("username") String username);

    @Query(value = "SELECT PASSWORD FROM TB_USER WHERE USERNAME =? 1", nativeQuery = true)
    String getPassword(String username);

    @Query(value = "SELECT INFLUENCER_ID FROM TB_INFLUENCER WHERE EMAIL = ?1", nativeQuery = true)
    Long findIdByEmail (String email);
    
    @Query(value = "SELECT EMAIL FROM TB_INFLUENCER WHERE USER_ID = ?1", nativeQuery = true)
    String findEmailById (Long id);
    

    @Query(value = "SELECT count(*) from trainer_subject \n"
            + "where class_id = ?1 and employee_id = ?2 and subject_id = ?3", nativeQuery = true)
    Long checkIfTrainerAlreadyAssigned(String classId, Long employeeId, Long subjectId);
}
