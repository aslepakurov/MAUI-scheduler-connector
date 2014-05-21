MAUI-scheduler-connector
========================

Back-end part of scheduler UI.
Based on Apache Servicemix 5.0.0 and MongoDB.

Additional configuration files:

    {KARAF_HOME}/etc/rest.properties    - rest service (example src/test/resources/rest.properties)
    {KARAF_HOME}/etc/db.properties      - database     (example src/test/resources/db.properties)

Webservices URLs:

    <GET> ../rest/maui/status           - get REST WS status
    <GET> ../rest/maui/requests         - get all requests
    <GET> ../rest/maui/request/{id}     - get request with {id}
    <GET> ../rest/maui/remove/{id}      - remove request with {id}
    <POST>../rest/maui/postrequest      - post request to the system (need "type" param in json body)
