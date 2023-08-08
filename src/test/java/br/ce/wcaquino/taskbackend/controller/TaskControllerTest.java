package br.ce.wcaquino.taskbackend.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.model.TaskBuilder;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {
	
	@Mock
	private TaskRepo repo;
	
	@InjectMocks
	private TaskController controller;
	
	@Before
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void naoDeveSalvarTaskSemDescricao() {
		Task todo = TaskBuilder.aTask()
					.withDueData(LocalDate.now())
					.build();
		try {
			controller.save(todo);
			fail("Não lançou exceção!");
		} catch (ValidationException e) {
			assertEquals("Fill the task description", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTaskSemData() {
		Task todo = TaskBuilder.aTask()
				.withTask("Descricao")
				.build();
		try {
			controller.save(todo);
			fail("Não lançou exceção!");
		} catch (ValidationException e) {
			assertEquals("Fill the due date", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTaskComDataPassada() {
		Task todo = TaskBuilder.aTask()
				.withTask("Descricao")
				.withDueData(LocalDate.of(2010, 1, 1))
				.build();
		try {
			controller.save(todo);
			fail("Não lançou exceção!");
		} catch (ValidationException e) {
			assertEquals("Due date must not be in past", e.getMessage());
		}
	}
	
	@Test
	public void deveSalvarTaskComSucesso() {
		Task todo = TaskBuilder.aTask()
				.withTask("Descricao")
				.withDueData(LocalDate.now())
				.build();
		try {
			controller.save(todo);
			verify(repo).save(todo);
		} catch (ValidationException e) {
			fail("ValidationException");
		}
	}

}
