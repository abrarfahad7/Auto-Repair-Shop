package ca.mcgill.ecse321.projectgroup04.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup04.model.CheckupReminder;
import ca.mcgill.ecse321.projectgroup04.model.Customer;

public interface CheckupReminderRepository extends CrudRepository<CheckupReminder, Long>{
    CheckupReminder findByReminderId(Long reminderId);
//
//	CheckupReminder findCheckupReminderByDate(Date date);
//

//
//	CheckupReminder findCheckupReminderByMessage(String message);
}
