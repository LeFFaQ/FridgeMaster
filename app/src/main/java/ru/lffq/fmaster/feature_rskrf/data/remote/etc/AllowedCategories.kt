package ru.lffq.fmaster.feature_rskrf.data.remote.etc

/**
 * Разрешенные категории.
 * Необходимо для фильтрации от ненужных категорий
 * в запросах getSubcategory
 */
enum class AllowedCategories(val categoryId: Int) {

    NUTRITION(8),
    DRINKS(28),
}