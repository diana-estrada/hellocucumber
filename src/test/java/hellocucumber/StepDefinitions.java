package hellocucumber;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
	
	public Integer number1;
	public Integer number2;
	public String operacion;
	
	@Given("I have entered {int} and {int} into calculator")
	public void i_have_entered_and_into_calculator(Integer int1, Integer int2) {
	    number1 = int1;
	    number2 = int2;
	}

	
	@When("I {string} number1 and number2")
	public void i_number1_and_number2(String string) {

		operacion = string;
	}
	
	@Then("I should be {double}")
	public void i_should_be(Double double1) {
	    
		List<String> argumentos = new ArrayList<String>();
		argumentos.add("./calculator2-exe");
		argumentos.add("12 13 'a'");
		//argumentos.add(number2.toString());
		//argumentos.add(operacion);
		
		ProcessBuilder p = new ProcessBuilder(argumentos);
        System.out.println("Started EXE");
        String command = "/tmp/workspace/Cobol_Demo/";
        String line = null;
        try {
        	p.directory(new File(command));
			BufferedReader reader = new BufferedReader(
                  new InputStreamReader(p.start().getInputStream()));
			
          while ((line = reader.readLine()) != null) {
              assertEquals(double1, new Double(Double.parseDouble(line)));
          }
          reader.close();
          
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
