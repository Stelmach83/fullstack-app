package eu.stelmaszak.fullstackapp.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DemoRepository extends JpaRepository<DemoEntity, UUID> {

}
