# 626. Exchange Seats
SELECT if(id <(SELECT COUNT(*)FROM seat),if(id MOD 2=0,id-1,id+1),if(id MOD 2=0,id-1,id))as id,student
FROM seat
ORDER BY id ASC ;