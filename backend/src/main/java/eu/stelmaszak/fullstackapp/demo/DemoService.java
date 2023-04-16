package eu.stelmaszak.fullstackapp.demo;

import eu.stelmaszak.fullstackapp.gen.model.CreateDemoRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoService {

    private final DemoRepository demoRepository;
    private final DemoMapper demoMapper;

    Boolean createDemo(CreateDemoRequestDto requestDto) {
        DemoEntity savedDemo = saveDemo(requestDto);
        return savedDemo.getId() != null;
    }

    @Transactional
    DemoEntity saveDemo(CreateDemoRequestDto requestDto) {
        DemoEntity savedDemo = demoRepository.save(demoMapper.mapToEntity(requestDto));
        log.info("Demo with id '%s' has been saved".formatted(savedDemo.getId()));
        return savedDemo;
    }
}
