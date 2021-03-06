package com.webempleos.app.specification;

import com.webempleos.app.models.entity.Publicacion;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class PublicacionSpecification {

    public static Specification<Publicacion> publicacionSpecification(Publicacion publicacion) {
        return (from, query, criteriaBuilder) -> {
            List<Predicate> condiciones = new ArrayList<>();
            if (publicacion.getTitulo() != null && !publicacion.getTitulo().trim().isEmpty()) {
                Predicate condicion = criteriaBuilder.like(from.get("titulo"), "%" + publicacion.getTitulo() + "%");
                condiciones.add(condicion);
            }

            if (publicacion.getDescripcion() != null && !publicacion.getDescripcion().trim().isEmpty()) {
                Predicate condicion = criteriaBuilder.like(from.get("descripcion"), "%" + publicacion.getDescripcion() + "%");
                condiciones.add(condicion);
            }

            if (publicacion.getCategoria() != null && !publicacion.getCategoria().getNombre().trim().isEmpty()) {
                Predicate condicion = criteriaBuilder.like(from.get("categoria").get("nombre"), "%" + publicacion.getCategoria().getNombre() + "%");
                condiciones.add(condicion);
            }
            return criteriaBuilder.or(condiciones.toArray(new Predicate[condiciones.size()]));
        };
    }
}
