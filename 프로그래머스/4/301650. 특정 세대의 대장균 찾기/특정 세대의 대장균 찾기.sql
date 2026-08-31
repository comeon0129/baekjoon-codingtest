SELECT ID
FROM ECOLI_DATA
-- 3단계: 내 부모가 2세대인 애들의 ID 찾기(3세대)
WHERE PARENT_ID IN(
     -- 2단계: 내 부모가 1세대인 애들의 ID 찾기 (2세대)
    SELECT ID
    FROM ECOLI_DATA
    WHERE PARENT_ID IN(
         -- 1단계: 최초의 조상(부모가 없는 애들) ID 찾기(1세대)
        SELECT ID
        FROM ECOLI_DATA
        WHERE PARENT_ID IS NULL
    )
)

   

ORDER BY ID ASC;