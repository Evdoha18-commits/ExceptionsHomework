import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {

    @Test
    public void shouldRemoveExistingProduct() {
        // Arrange (Подготовка)
        ShopRepository repo = new ShopRepository();
        Product product = new Product(1, "Milk", 100);
        repo.add(product);

        // Act (Действие)
        repo.removeById(1);

        // Assert (Проверка)
        assertNull(repo.findById(1), "Товар должен быть удален");
        assertEquals(0, repo.findAll().length, "Массив товаров должен быть пуст");
    }

    @Test
    public void shouldThrowExceptionWhenRemovingNonExistingProduct() {
        // Arrange
        ShopRepository repo = new ShopRepository();
        Product product = new Product(1, "Bread", 50);
        repo.add(product);

        // Act & Assert
        // Мы ожидаем, что при вызове removeById(99) вылетит NotFoundException
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            repo.removeById(99);
        });

        // Проверяем, что сообщение исключения совпадает с требуемым
        assertEquals("Element with id: 99 not found", exception.getMessage());
    }
}