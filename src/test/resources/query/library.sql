
-- How many users that is active?
select * from users
where status='ACTIVE';

select count(*) from users
where status='ACTIVE' and user_group_id <> 1;

-- How many users that is inactive?
select count(*) from users
where status='INACTIVE' ;

-- How many book we have in each category?
select bc.name ,count(*)
from books b
    join book_categories bc
        on bc.id=b.book_category_id
group by bc.name;

-- find the most popular book category

select bc.name, count(*)
from book_categories bc
        join books b on bc.id=b.book_category_id
        join book_borrow bb on b.id = bb.book_id
group by bc.name
order by count(*) desc;

-- How many book we have in 'Anthology' category?

select count(*) from books b
                join book_categories bc on bc.id=b.book_category_id
where bc.name= 'Anthology';



















