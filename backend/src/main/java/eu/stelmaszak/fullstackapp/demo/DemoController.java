package eu.stelmaszak.fullstackapp.demo;

import eu.stelmaszak.fullstackapp.gen.api.DemoControllerApi;
import eu.stelmaszak.fullstackapp.gen.model.CreateDemoRequestDto;
import eu.stelmaszak.fullstackapp.gen.model.CreatedDemoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController implements DemoControllerApi {

    private final DemoService demoService;

    @PreAuthorize("hasAuthority('app_user')")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public CreatedDemoResponseDto createDemo(CreateDemoRequestDto createDemoRequestDto) {
        return demoService.createDemo(createDemoRequestDto);
    }
}
