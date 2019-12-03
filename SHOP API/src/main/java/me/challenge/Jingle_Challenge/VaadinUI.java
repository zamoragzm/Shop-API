package me.challenge.Jingle_Challenge;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.Route;
import me.challenge.Jingle_Challenge.entity.Product;
import me.challenge.Jingle_Challenge.repository.ProductRepository;


@Route("UI")
public class VaadinUI extends VerticalLayout {

    private ProductRepository productRepository;

    private Grid<Product> grid = new Grid<>(Product.class);

    public VaadinUI(ProductRepository productRepository) {
        this.productRepository = productRepository;
        grid.setColumns("id", "productName", "productNumber","price");

        add(grid);

        setSizeFull();

        updateList();
    }

    private void updateList() {
        grid.setItems(productRepository.findAll());
    }


}


