package in.bushansirgur.expensemanager.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {
	
	private Long id;
	
	private String expenseId;

	@NotBlank(message = "Expense name should not be empty")
	@Size(min = 3, message = "Expense name should be at least {min} characters")
	private String name;
	
	private String description;

	@NotNull(message = "Expense amount should not be null")
	@Min(value = 1, message = "Expense amount should not be less than 1")
	private BigDecimal amount;
	
	private Date date;
	
	private String dateString; 

}
