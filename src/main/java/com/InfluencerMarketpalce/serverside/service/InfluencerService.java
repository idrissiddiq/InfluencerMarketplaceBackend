/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.ChangePasswordDTO;
import com.InfluencerMarketpalce.serverside.model.Influencer;
import com.InfluencerMarketpalce.serverside.model.Job;
import com.InfluencerMarketpalce.serverside.model.response.EmployeeRequest;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;
import com.InfluencerMarketpalce.serverside.model.response.ProfileDTO;
import com.InfluencerMarketpalce.serverside.model.response.ResponseMessage;
import com.InfluencerMarketpalce.serverside.repository.InfluencerRepository;
import com.InfluencerMarketpalce.serverside.repository.JobRepository;
import com.InfluencerMarketpalce.serverside.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class InfluencerService extends ResponseStatus {

    private InfluencerRepository influencerRepository;
    private JobRepository jobRepository;
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Autowired
    public InfluencerService(InfluencerRepository influencerRepository, JobRepository jobRepository, UserRepository userRepository, PasswordEncoder encoder) {
        this.influencerRepository = influencerRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public List<Influencer> findAll() {
        return influencerRepository.findAll();
    }
    
    public List<Influencer> findAllExcept() {
        return influencerRepository.findAllExceptAdmin();
    }
    
//    public String findEmail(ForgotPasswordRequest request){
//        String email = request.getEmail();
//        String check = employeeRepository.findEmail(email);
//        if(check == null){
//            return "unique";
//        }
//        return "duplicate";
//    }
    


    public ProfileDTO findUserProfileInfo(String name) {
        Long id = userRepository.findIdByUsername(name);
        return influencerRepository.getProfileInfo(id);
    }

    public List<Influencer> findEmployeeByClassId(String id) {
        return influencerRepository.findEmployeeByClassId(id);
    }

    public List<Influencer> findAllEmployeeWithNoClass() {
        return influencerRepository.findAllEmployeeWithNoClass();
    }

    public List<Influencer> findAllTrainer() {
        return influencerRepository.findAllTrainer();
    }

    public Influencer findById(Long id) {
        Influencer data = influencerRepository.findById(id).orElseThrow(this::dataNotFound);
        return data;
    }

    public Influencer changePassword(ChangePasswordDTO request, String username) {

        influencerRepository.changePassword(encoder.encode(request.getNewPassword()), username);
        Long id = userRepository.findIdByUsername(username);
        Influencer data = influencerRepository.findById(id).orElseThrow(this::dataNotFound);
        return data;

    }

//    public RegisterEmployeeRequest regist(RegisterEmployeeRequest request) {
//       Employee employee = new Employee();
//        employee.setId(request.getId());
//        employee.setFullname(request.getFullname());
//        employee.setEmail(request.getEmail());
//
//        User user = new User();
//        user.setEmployees(employee);
//        user.setId(request.getId());
//        user.setUsername(request.getUsername());
//        user.setPassword(request.encode("pass"));
//        employee.setUser(user);
//
//        employeeRepository.save(employee);
//
//        return new RegisterEmployeeRequest(request.getId(), request.getName());
//    }
    
    
    
    public ResponseMessage<EmployeeRequest> create(EmployeeRequest request) {
        long temp = influencerRepository.findEmail(request.getEmail());
        if (temp >= 1) {
            return new ResponseMessage<>("Error - Email Already Registered",new EmployeeRequest(request.getId(), request.getFullname(), request.getEmail(),
                request.getJobId()));
        }
        Influencer employee = new Influencer();
        employee.setId(request.getId());
        employee.setFullname(request.getFullname());
        employee.setEmail(request.getEmail());
        Job job = jobRepository.findByIdJob(request.getJobId());
        employee.setJob(job);

        influencerRepository.save(employee);

        return new ResponseMessage<>("Employee Created",new EmployeeRequest(request.getId(), request.getFullname(), request.getEmail(),
                request.getJobId()));

    }

    public EmployeeRequest update(EmployeeRequest request, Long id) {
        Optional<Influencer> temp = influencerRepository.findById(id);
        if (!temp.isPresent()) {
            throw dataNotFound();
        }
        Influencer employee = new Influencer();
        employee.setId(request.getId());
        employee.setFullname(request.getFullname());
        employee.setEmail(request.getEmail());
        Job job = jobRepository.findByIdJob(request.getJobId());
        employee.setJob(job);

        influencerRepository.save(employee);

        return new EmployeeRequest(request.getId(), request.getFullname(), request.getEmail(),
                request.getJobId());
    }

    public Influencer removeEmployeeFromClass(Long id, String classId) {
        Influencer data = influencerRepository.findById(id).orElseThrow(this::dataNotFound);
        influencerRepository.removeEmployeeFromClass(id, classId);
        return data;
    }

    public Influencer removeTrainerFromClass(Long trainerSubjectId, Long employeeId) {
        Influencer data = influencerRepository.findById(employeeId).orElseThrow(this::dataNotFound);
        influencerRepository.removeTrainerFromClass(trainerSubjectId);
        return data;
    }

    public Influencer delete(Long id) {
        Influencer data = influencerRepository.findById(id).orElseThrow(this::dataNotFound);
        influencerRepository.deleteById(id);
        return data;
    }

//    public Employee update(Employee employee, Long id) {
//        Optional<Employee> temp = employeeRepository.findById(id);
//        if (!temp.isPresent()) {
//            throw dataNotFound();
//        }
//        return employeeRepository.save(employee);
//    }
}
