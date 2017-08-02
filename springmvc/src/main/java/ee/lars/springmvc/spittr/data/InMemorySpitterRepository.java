package ee.lars.springmvc.spittr.data;

import ee.lars.springmvc.spittr.Spitter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class InMemorySpitterRepository implements SpitterRepository {

    @Override
    public Spitter save(Spitter unsaved) {
        return null;
    }

    @Override
    public Spitter findByUsername(String username) {
        return null;
    }
}