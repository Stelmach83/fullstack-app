package eu.stelmaszak.fullstackapp.demo;

import eu.stelmaszak.fullstackapp.gen.api.DemoControllerApi;
import eu.stelmaszak.fullstackapp.gen.model.CreateDemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController implements DemoControllerApi {

    private final DemoService demoService;

    @Override
    public Boolean createDemo(CreateDemoRequestDto createDemoRequestDto) {
        demoService.createDemo(createDemoRequestDto);
        return true;
    }
}
