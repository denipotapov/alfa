@cycle
Feature: AddBikeToCartAndClearCart
  Adds one bike to cart and if it exists in it, clear the cart

Scenario: Buy bicycle
    Given Home page is opened
    When "Спорт и Туризм" is opened
    When Navigated to "Тренажеры" by "Все категории"
    Then "Велотренажеры" category contains 5 entries
    And "Велотренажеры" category exists of "[Портативный, Вертикальный, Гибридный, Горизонтальный, Ременной]"
    When Navigated to "Велотренажеры" by "Гибридный"
    Then List of goods contains 1 card
    When Navigated to 1 card
    Then Article equals "3590294"
    When 'Наименование товара' assigned to 'productName'
    And Navigated to buy good
    Then "productName" good is in cart
    When Navigated to cart
    Then Clear the cart