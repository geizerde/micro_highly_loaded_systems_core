package ru.hpclab.hl.module1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hpclab.hl.module1.service.DatabaseCleanupService;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

@RestController
@RequestMapping("/clear")
@RequiredArgsConstructor
public class DatabaseCleanupController {
    private final DatabaseCleanupService cleanupService;
    private final ObservabilityService observabilityService;

    @DeleteMapping
    public void clearDatabase() {
        observabilityService.start(getClass().getSimpleName() + ":clearDatabase");

        cleanupService.clear();

        observabilityService.stop(getClass().getSimpleName() + ":clearDatabase");
    }
}
