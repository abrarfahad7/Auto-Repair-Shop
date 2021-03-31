package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FieldTechnicianService {

    @Autowired
    private FieldTechnicianRepository fieldTechnicianRepository;
    @Autowired
    private EmergencyServiceRepository emergencyServiceRepository;

    @Transactional
    public FieldTechnician createFieldTechnician(String name) {

        if (name == "") {
            throw new IllegalArgumentException("Name can't be empty");
        }
        FieldTechnician existingFieldTechnician = getFieldTechnicianByName(name);
        if (existingFieldTechnician != null) {
            throw new IllegalArgumentException("Field Technician with this name already exists");
        }

        FieldTechnician fieldTechnician = new FieldTechnician();
        fieldTechnician.setName(name);
        fieldTechnician.setIsAvailable(true);

        fieldTechnicianRepository.save(fieldTechnician);
        return fieldTechnician;
    }

    @Transactional
    public FieldTechnician getFieldTechnicianById(Long technicianId) {
        return fieldTechnicianRepository.findFieldTechnicianByTechnicianId(technicianId);
    }

    @Transactional
    public List<FieldTechnician> getAllFieldTechnicians() {
        return (List<FieldTechnician>) fieldTechnicianRepository.findAll();
    }

    @Transactional
    public FieldTechnician getFieldTechnicianByName(String name) {
        return fieldTechnicianRepository.findFieldTechnicianByName(name);
    }

    @Transactional
    public FieldTechnician deleteFieldTechnician(FieldTechnician fieldTechnician) {
        List<EmergencyService> emergencyServices = (List<EmergencyService>) emergencyServiceRepository.findAll();

        for (EmergencyService emergencyService : emergencyServices) {
            if (emergencyService.getTechnician().equals(fieldTechnician)) {
                throw new IllegalArgumentException("The field technician is assigned to an emergency service!");
            }
        }
        fieldTechnicianRepository.delete(fieldTechnician);
        fieldTechnician = null;
        return fieldTechnician;

    }

    @Transactional
    public void editFieldTechnician(FieldTechnician fieldTechnician, String name) {
        fieldTechnician.setName(name);
        fieldTechnicianRepository.save(fieldTechnician);
    }
}
