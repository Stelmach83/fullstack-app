package eu.stelmaszak.fullstackapp.demo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity(name = "demo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class DemoEntity {

    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    UUID id;

    String name;

    String description;
}
