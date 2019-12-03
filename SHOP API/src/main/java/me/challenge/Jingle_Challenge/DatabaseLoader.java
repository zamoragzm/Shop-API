package me.challenge.Jingle_Challenge;

import me.challenge.Jingle_Challenge.entity.*;
import me.challenge.Jingle_Challenge.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;
    private final AttributeRepository attributeRepository;
    private final ValueRepository valueRepository;

    @Autowired
    public DatabaseLoader(ProductRepository productRepository, CategoryRepository categoryRepository, StoreRepository storeRepository,
    AttributeRepository attributeRepository, ValueRepository valueRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.storeRepository = storeRepository;
        this.attributeRepository = attributeRepository;
        this.valueRepository = valueRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
       Product p1 = new Product("p1","p1",1);
       Product p2 = new Product("p2","p2",2);
       Product p3 = new Product("p3","p3",4);
       Product p4 = new Product("p4","p4",5);
       Product p5 = new Product("p5","p5",7);

        Category c1 = new Category("sports",p1);
        Category c2 = new Category("running",p1);
        Category c3 = new Category("sports",p2);
        Category c4 = new Category("technology",p3);
        Category c5 = new Category("toys",p4);
        Category c6 = new Category("sports",p5);

        Store s1 = new Store("s1","as1",20,p1);
        Store s2 = new Store("s2","as2",2,p1);
        Store s3 = new Store("s3","as3",24,p2);
        Store s4 = new Store("s4","as4",65,p3);
        Store s5 = new Store("s5","as5",1,p4);
        Store s6 = new Store("s6","as6",98,p5);

        Attribute a1 = new Attribute("Color",p1);
        Attribute a2 = new Attribute("Size",p1);
        Attribute a3 = new Attribute("Weight",p2);
        Attribute a4 = new Attribute("Color",p3);
        Attribute a5 = new Attribute("Color",p4);
        Attribute a6 = new Attribute("Color",p5);

        Value v1 = new Value("Blue",a1);
        Value v2 = new Value("Red",a1);
        Value v3 = new Value("98inches",a2);
        Value v4 = new Value("22kg",a3);
        Value v5 = new Value("Black",a4);
        Value v6 = new Value("White",a5);
        Value v7 = new Value("Yellow",a6);

        this.productRepository.save(p1);
        this.productRepository.save(p2);
        this.productRepository.save(p3);
        this.productRepository.save(p4);
        this.productRepository.save(p5);

        this.categoryRepository.save(c1);
        this.categoryRepository.save(c2);
        this.categoryRepository.save(c3);
        this.categoryRepository.save(c4);
        this.categoryRepository.save(c5);
        this.categoryRepository.save(c6);

        this.storeRepository.save(s1);
        this.storeRepository.save(s2);
        this.storeRepository.save(s3);
        this.storeRepository.save(s4);
        this.storeRepository.save(s5);
        this.storeRepository.save(s6);

        this.attributeRepository.save(a1);
        this.attributeRepository.save(a2);
        this.attributeRepository.save(a3);
        this.attributeRepository.save(a4);
        this.attributeRepository.save(a5);
        this.attributeRepository.save(a6);

        this.valueRepository.save(v1);
        this.valueRepository.save(v2);
        this.valueRepository.save(v3);
        this.valueRepository.save(v4);
        this.valueRepository.save(v5);
        this.valueRepository.save(v6);
        this.valueRepository.save(v7);




        System.out.println("\nData is created successfully...\n");
    }
}
