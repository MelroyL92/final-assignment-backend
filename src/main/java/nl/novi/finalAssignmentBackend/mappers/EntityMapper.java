package nl.novi.finalAssignmentBackend.mappers;

public interface EntityMapper <Model, Entity> {

    Model fromEntity(Entity entity);

    Entity toEntity(Model model);

}
