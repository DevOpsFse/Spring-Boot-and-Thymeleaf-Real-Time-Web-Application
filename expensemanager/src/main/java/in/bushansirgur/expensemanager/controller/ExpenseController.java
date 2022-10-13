package in.bushansirgur.expensemanager.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import in.bushansirgur.expensemanager.dto.ExpenseFilterDTO;
import in.bushansirgur.expensemanager.util.DateTimeUtil;
import in.bushansirgur.expensemanager.validator.ExpenseValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import in.bushansirgur.expensemanager.dto.ExpenseDTO;
import in.bushansirgur.expensemanager.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ExpenseController {
	
	private final ExpenseService expenseService;
	
	@GetMapping("/expenses")
	public String showExpenseList(Model model) {
		List<ExpenseDTO> list = expenseService.getAllExpenses();
		model.addAttribute("expenses", list);
		model.addAttribute("filter", new ExpenseFilterDTO(DateTimeUtil.getCurrentMonthStartDate(), DateTimeUtil.getCurrentMonthDate()));
		String totalExpenses = expenseService.totalExpenses(list);
		model.addAttribute("totalExpenses", totalExpenses);
		return "expenses-list";
	}
	@GetMapping("/createExpense")
	public String showExpenseForm(Model model) {
		model.addAttribute("expense", new ExpenseDTO());
		return "expense-form";
	}

	@PostMapping("/saveOrUpdateExpense")
	public String saveOrUpdateExpenseDetails(@Valid @ModelAttribute("expense") ExpenseDTO expneseDTO,
											 BindingResult result) throws ParseException {
		System.out.println("Printing the Expense DTO: "+expneseDTO);

		new ExpenseValidator().validate(expneseDTO, result);

		if (result.hasErrors()) {
			return "expense-form";
		}
		expenseService.saveExpenseDetails(expneseDTO);
		return "redirect:/expenses";
	}

	@GetMapping("/deleteExpense")
	public String deleteExpense(@RequestParam String id) {
		System.out.println("Printing the expense Id:"+id);
		expenseService.deleteExpense(id);
		return "redirect:/expenses";
	}

	@GetMapping("/updateExpense")
	public String updateExpense(@RequestParam String id, Model model) {
		System.out.println("Printing the expense Id inside update method:"+id);
		ExpenseDTO expense = expenseService.getExpenseById(id);
		model.addAttribute("expense", expense);
		return "expense-form";
	}
}






















