package com.example.demo.Adapter.Controller;

import com.example.demo.Adapter.Command.CreateClientCommand;
import com.example.demo.Adapter.DTO.CreateClientDTO;
import com.example.demo.Application.UseCase.CreateClientUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("client")
@RestController
public class CreateClientController {
    private CreateClientUseCase createClientUseCase;

    public CreateClientController(CreateClientUseCase createClientUseCase) {
        this.createClientUseCase = createClientUseCase;
    }

    @PostMapping("/create")
    public void createClient(@RequestBody CreateClientDTO createClientDTO) {
        CreateClientCommand createClientCommand = new CreateClientCommand(createClientDTO.getName(), createClientDTO.getFirstName());
        createClientUseCase.createClient(createClientCommand);
    }
}
