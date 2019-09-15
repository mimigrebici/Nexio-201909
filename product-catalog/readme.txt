

1- Installation de l'application:

		I- Installer le JRE version 8 ou plus, et exécuter le fichier: product-catalog-0.0.1-SNAPSHOT.jar
		
	   II- Aller sur le browser web ou sur postman ou tout autre outil de test de web services
		
	  III- Consulter la liste ci-dessous afin de connaître: 
		    + la liste des logins/password des utilisateurs que vous pouvez utiliser pour vous connecter à l'application
		    + la liste des références des catalogues disponibles (pour remplacer [CATALOG-REF] dans les URLs du point IV)
		    + la liste des références des produits disponibles (pour remplacer [PRODUCT-REF]  dans les URLs du point IV)
		   
		    
	  IV- Saisir une à une les URLs suivantes pour accéder aux fonctionnalités demandées: une authentification vous sera demandée à la première connexion
		     (voir la liste des login/password ci-dessous)
		
			1- Afficher un catalogue de produits:    localhost:8080/catalogs/[CATALOG-REF] 
			2- Afficher le détail d’un produit:      localhost:8080/products/[PRODUCT-REF]
			3- Ajouter un produit au panier:         localhost:8080/cart/[PRODUCT-REF]/add
			4- Enlever un produit du panier:         localhost:8080/cart/[PRODUCT-REF]/remove
			5- Afficher le contenu du panier:        localhost:8080/cart/products
			
	   V- pour se déconnecter, utiliser : localhost:8080/logout
	
	
2- Liste des données nécessaires pour tester l'application:

			I- Liste des utilisateurs avec lesquels vous pouvez vous connecter: 
			
						 a- login: user1, password: pass
						 b- login: user2, password: pass
						 c- login: user3, password: pass
						 d- login: user4, password: pass
						 
			II- Liste des références des catalogues disponibles et leurs produits:
			
						 a- référence du Catalogue 1: BOOK-REF
						     a.1- référence du produit 1 : BK-HC-JAVA-126
						     a.2- référence du produit 2 : BK-HC-JAVA-MS-106
						     a.3- référence du produit 3 : BK-HC-JAVA-DP-006
						 
						 b- référence du Catalogue 2: DEVICE-REF
						     b.1- référence du produit 1 : LISEN-IP-CG-6FT
						     b.2- référence du produit 2 : LETSCOM-BT-HP-IPX7
						 
						 c- référence du catalogue 3: MUSIC-REF
						     c.1- référence du produit 1 : QUEEN-GH-123
						     c.2- référence du produit 2 : PINK-FD-DSM-5698
		
