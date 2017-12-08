
BUAT DATABASE 

BUAT TABLE SESUAI DENGAN SPESIFIKASI PERINTAH YANG DISURUH

IMPORT FILE KNJ_DOS,KNJ_KUNCI

TAMBAHKAN FIELD SKORE,RASIO,GRADE

MASUK KE NETBEANS

BIKIN PROJECT BARU COPY FILE MYSQL dan COBA terserah mau diganti atau enggak namanya

add jar diproject mysql connection jar nya ada di github

Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sbd_takehome?zeroDateTimeBehavior=convertToNull",USER,PASS);

sbd_takehome nya ganti sama nama database yang udah dibuat

tinggal run programnya
