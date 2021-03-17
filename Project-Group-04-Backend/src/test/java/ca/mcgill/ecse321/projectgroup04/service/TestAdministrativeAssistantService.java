package ca.mcgill.ecse321.projectgroup04.service;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup04.service.AutoRepairShopSystemService;
import ca.mcgill.ecse321.projectgroup04.dao.AdministrativeAssistantRepository;
import ca.mcgill.ecse321.projectgroup04.model.AdministrativeAssistant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class TestAdministrativeAssistantService {
	
	
	@Mock
	private AdministrativeAssistantRepository administrativeAssistantRepository;
	
	@InjectMocks
	private AutoRepairShopSystemService service;
	
	 private static final String ADMINASSISTANT_NAME = "TestName";
	 private static final String ADMINASSISTANT_PASSWORD = "TestPassword";
	
	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
		lenient().when(administrativeAssistantRepository.save(any(AdministrativeAssistant.class))).thenAnswer(returnParameterAsAnswer);
        
        lenient().when(administrativeAssistantRepository.findAdministrativeAssistantByUserId(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ADMINASSISTANT_NAME)) {

                AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
                administrativeAssistant.setUserId(ADMINASSISTANT_NAME);
                administrativeAssistant.setPassword(ADMINASSISTANT_PASSWORD);
            
               
                return administrativeAssistant;
            }
            return null;
        });

    }
	
	@Test
    public void TestCreateAdministrativeAssistant() {
	
		String userId = "Testname";
        String password = "TestPassword";
        
        AdministrativeAssistant administrativeAssistant = null;
        try {
        	administrativeAssistant = service.createAdministrativeAssistant(userId, password);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(administrativeAssistant);
        assertEquals(userId, administrativeAssistant.getUserId());
        assertEquals(password, administrativeAssistant.getPassword());

	}
	
//	 @Test
//	    public void TestCreateAdministrativeAssistantNoUserId() {
//	        String userId = "";
//	        String password = "TestPassword";
//	       
//
//	        String error = null;
//
//	       
//
//	        AdministrativeAssistant administrativeAssistant = null;
//	        try {
//	        	administrativeAssistant = service.createAdministrativeAssistant(userId, password);
//	        } catch (IllegalArgumentException e) {
//	            error = e.getMessage();
//	        }
//
//	        assertNull(administrativeAssistant);
//	        assertEquals(error, "Username can't be empty");
//
//	    }
//
//	 @Test
//	 public void TestCreateAdministrativeAssistantThatAlreadyExists() {
//	        String userId = "TestName";
//	        String password = "TestPassword";
//	        String error = null;
//
//	        AdministrativeAssistant administrativeAssistant = null;
//	        try {
//	        	administrativeAssistant = service.createAdministrativeAssistant(userId, password);
//	        } catch (IllegalArgumentException e) {
//	            error = e.getMessage();
//	        }
//
//	        assertNull(administrativeAssistant);
//	        assertEquals(error, "Administrative Assistant already exists");
//
//	    }
	 @Test
	 public void TestCreateAdministrativeAssistantNoPassword() {
	        String userId = "TestUserId";
	        String password = "";
	       

	        String error = null;

	      
	        AdministrativeAssistant administrativeAssistant = null;
	        try {
	        	administrativeAssistant = service.createAdministrativeAssistant(userId, password);
	        } catch (IllegalArgumentException e) {
	            error = e.getMessage();
	        }

	        assertNull(administrativeAssistant);
	        assertEquals(error, "Password can't be empty");

	    }
	 
	
	 
		
}
	
	