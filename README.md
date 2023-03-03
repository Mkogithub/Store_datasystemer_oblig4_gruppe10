# Oblig 4 in the UiT course Store Datasystemer, for group 10


Repository for Oblig 3, the earlier version of the project can be found here: https://github.com/Mkogithub/Store_datasystemer_oblig3_gruppe10_maven.
this is where the current version of the app is.


# Run Instructions:
### for the api to be able to run, the following docker commands must be run:
  - docker run -d -p 9042:9042 --rm --name cassandra  cassandra:3.11
  - docker exec -it cassandra cqlsh
  #### (( Inside docker exec)
  - CREATE KEYSPACE spring_cassandra WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};
      
      #### Cassandra will for demonstration purposes be set up with only one node, and a replication_factor of one
  
  #### build and run from Ide.
