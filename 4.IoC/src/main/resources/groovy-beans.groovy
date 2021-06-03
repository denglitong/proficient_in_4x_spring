import com.denglitong.reflect.Car

/*
基于 Groovy DSL 来进行 Bean 定义配置，
可实现复杂、灵活的 Bean 配置逻辑
*/
beans {
    blueCar(Car) {
        brand = "红旗 CA72"
        color = "蓝色"
        maxSpeed = "200"
    }
}