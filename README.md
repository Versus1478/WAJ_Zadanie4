# Zadanie 4 – Screenshoty z Postmana

## POST – vytvorenie zamestnanca

### POST 201 Created

![POST 201 Created](src/main/resources/img/01_POST_201_Created.png)

### POST 409 Conflict – duplicitný email

![POST 409 Conflict – duplicitný email](src/main/resources/img/02_POST_409_Conflict_duplicitny_email.png)

### POST 400 Bad Request – viac chýb naraz (začiatok odpovede)

![POST 400 Bad Request – viac chýb naraz (začiatok odpovede)](src/main/resources/img/03_POST_400_viac_chyb_cast1_zaciatok.png)

### POST 400 Bad Request – viac chýb naraz (koniec odpovede)

![POST 400 Bad Request – viac chýb naraz (koniec odpovede)](src/main/resources/img/04_POST_400_viac_chyb_cast2_koniec.png)

### POST 400 Bad Request – email bez domény

![POST 400 Bad Request – email bez domény](src/main/resources/img/05_POST_400_email_bez_domeny.png)

### POST 400 Bad Request – email s dvoma bodkami

![POST 400 Bad Request – email s dvoma bodkami](src/main/resources/img/06_POST_400_email_dve_bodky.png)

## GET – načítanie zamestnancov

### GET 200 OK – všetci zamestnanci (začiatok odpovede)

![GET 200 OK – všetci zamestnanci (začiatok odpovede)](src/main/resources/img/07_GET_200_vsetci_zamestnanci_cast1_zaciatok.png)

### GET 200 OK – všetci zamestnanci (koniec odpovede)

![GET 200 OK – všetci zamestnanci (koniec odpovede)](src/main/resources/img/07_GET_200_vsetci_zamestnanci_cast2_koniec.png)

### GET 200 OK – zamestnanec podľa ID

![GET 200 OK – zamestnanec podľa ID](src/main/resources/img/08_GET_200_zamestnanec_podla_id.png)

### GET 404 Not Found – neexistujúce ID

![GET 404 Not Found – neexistujúce ID](src/main/resources/img/09_GET_404_neexistujuce_id.png)

### GET 400 Bad Request – neplatné ID (abc)

![GET 400 Bad Request – neplatné ID (abc)](src/main/resources/img/10_GET_400_neplatne_id_abc.png)

## PUT – aktualizácia zamestnanca

### PUT 200 OK

![PUT 200 OK](src/main/resources/img/11_PUT_200_aktualizacia.png)

### PUT 400 Bad Request – neplatný email

![PUT 400 Bad Request – neplatný email](src/main/resources/img/12_PUT_400_neplatny_email.png)

### PUT 404 Not Found – neexistujúce ID

![PUT 404 Not Found – neexistujúce ID](src/main/resources/img/13_PUT_404_neexistujuce_id.png)

### PUT 409 Conflict – email patrí inému zamestnancovi

![PUT 409 Conflict – email patrí inému zamestnancovi](src/main/resources/img/14_PUT_409_Conflict_email_patri_inemu.png)

## DELETE – zmazanie zamestnanca

### DELETE 200 OK

![DELETE 200 OK](src/main/resources/img/15_DELETE_200_zmazanie.png)

### DELETE 404 Not Found – už zmazaný zamestnanec

![DELETE 404 Not Found – už zmazaný zamestnanec](src/main/resources/img/16_DELETE_404_uz_zmazany.png)

### DELETE 400 Bad Request – neplatné ID (abc)

![DELETE 400 Bad Request – neplatné ID (abc)](src/main/resources/img/17_DELETE_400_neplatne_id_abc.png)

## Zoradenie a vyhľadávanie

### GET 200 OK – zoradenie podľa priezviska (začiatok odpovede)

![GET 200 OK – zoradenie podľa priezviska (začiatok odpovede)](src/main/resources/img/18_GET_200_zoradeni_podla_priezviska_cast1_zaciatok.png)

### GET 200 OK – zoradenie podľa priezviska (koniec odpovede)

![GET 200 OK – zoradenie podľa priezviska (koniec odpovede)](src/main/resources/img/18_GET_200_zoradeni_podla_priezviska_cast2_koniec.png)

### GET 200 OK – vyhľadávanie podľa mena a priezviska

![GET 200 OK – vyhľadávanie podľa mena a priezviska](src/main/resources/img/19_GET_200_hladanie_podla_mena.png)

### GET 400 Bad Request – chýba parameter lastName

![GET 400 Bad Request – chýba parameter lastName](src/main/resources/img/20_GET_400_hladanie_chyba_parameter.png)
