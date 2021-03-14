# cities-project

Demo Spring boot project that exposes a few services with city names. There are 2 main endpoints that give us the following JSON

```
{
 "content": [
 {"id": 75,"name": "Addis Ababa"},
 {"id": 297,"name": "Adelaide"},
 {"id": 124,"name": "Agadir"},
 {"id": 197,"name": "Agra"}, 
 {"id": 43,"name": "Ahmadabad"}
 ],
 "totalPages": 67,
 "totalElements": 332
}
```

## /api/cities/queryByPage?page={page_number}&size={items_by_page}&name={name_filter}

This API gives us cities based on their name with pagination


## /api/cities/algorithm

This API gives us  the longest possible sequence of city Id's which form an ascending list of numbers (the cities are oredered alphabetically).
