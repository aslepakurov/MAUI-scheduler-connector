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

MAUI library

    As there is no public repo for maui.jar and latest build number is unknown to add lib to lacal repo typo in terminal

        mvn install:install-file -Dfile="maui-1.0.jar" -DgroupId=unm -DartifactId=maui -Dversion=1.0 -Dpackaging=jar


Request ist

    3. canceljob

    Cancel a job, and remove it from the system.

    3.1 Send

            <mauicomm version="0.5" comm="canceljob" ts="[TIMESTAMP]">
            <canceljob user="[USER]" jobid="[JOBID]"/>
            </mauicomm>

        The TIMESTAMP is the UNIX epoch time in seconds.
        The USER is the user executing the command.
        The JOBID is the job to cancel.


    4. cancelres

    Cancel a sys reservation, and remove it from the system.

    4.1 Send

            <mauicomm version="0.5" comm="cancelres" ts="[TIMESTAMP]">
            <cancelres user="[USER]" resid="[RESID]"/>
            </mauicomm>

        The TIMESTAMP is the UNIX epoch time in seconds.
        The USER is the user executing the command.
        The RESID is the reservation to cancel.

    5. checkjob

    Check the various parameters of a job. Unless you're a Maui Scheduler administrator, you will only be able to view your own jobs.

    5.1 Send

            <mauicomm version="0.5" comm="checkjob" ts="[TIMESTAMP]">
            <checkjob user="[USER]" jobid="[JOBID]" verbose="[VERB]"/>
            </mauicomm>

        The TIMESTAMP is the UNIX epoch time in seconds.
        The USER is the user executing the command.
        The JOBID is the job in question.
        VERB true or false for verbosity (or remove attribute altogether).

    6. checknode

    Check the node(s).

    6.1 Send

            <mauicomm version="0.5" comm="checknode" ts="[TIMESTAMP]">
            <checknode user="[USER]" nodeid="[NODEID]" verbose="[VERB]" feature="[FEATURE]"/>
            </mauicomm>

        The TIMESTAMP is the UNIX epoch time in seconds.
        The USER is the user executing the command.
        The NODEID is the node in question. It should be a valid node ID, or the string ALL, in which case you'll get information for every node.
        VERB true or false for verbosity (or remove attribute altogether).
        The FEATURE is the particular feature to report. The verbose setting will trump the feature setting for the reponse.

    7. checkres

    Check the reservation.

    7.1 Send

            <mauicomm version="0.5" comm="checkres" ts="[TIMESTAMP]">
            <checkres user="[USER]" resid="[RESID]"/>
            </mauicomm>

        The TIMESTAMP is the UNIX epoch time in seconds.
        The USER is the user executing the command.
        The RESID is the reservation in question.

    8. holdjob

    Hold a job indefinately.

    8.1 Send

            <mauicomm version="0.5" comm="holdjob" ts="[TIMESTAMP]">
            <holdjob user="[USER]" jobid="[JOBID]"/>
            </mauicomm>

        The TIMESTAMP is the UNIX epoch time in seconds.
        The USER is the user executing the command.
        The JOBID is the job in question.

    9. mauicontrol

    Scheduler miscellaneous commands.

    9.1 Send

            <mauicomm version="0.5" comm="mauicontrol" ts="[TIMESTAMP]">
            <mauicontrol user="[USER]" control="[COMM]" token="[TOKEN]"/>
            </mauicomm>

        The TIMESTAMP is the UNIX epoch time in seconds.
        The USER is the user executing the command.
        The COMM is the command to perform
            ping - Ping scheduler
            uptime - Check scheduler uptime
            stop - Stop scheduler (admin only)
            pause - Pause scheduler (admin only)
            release - Release scheduler (from pause) (admin only)
            log - Start logging a particular token (admin only)
            unlog - Stop logging a particular token (admin only)
        The TOKEN is used to supply an additional argument to the command. This attribute can be removed entirely if it is not needed.

    10. mauisubmit

    Submit a job to the scheduler.

    10.1 Send

            <mauicomm version="0.5" comm="mauisubmit" ts="[TIMESTAMP]">
            <mauisubmit user="[USER]" jobuser="[JOBUSER]" jobgroup="[JOBGROUP]" block>
            <envp><![CDATA[
            [ENVIRONMENT]
            ]]></envp>
            <cmd flags="w"><![CDATA[
            [CMDFILE]
            ]]></cmd>
            </mauisubmit>
            </mauicomm>

        The TIMESTAMP is the UNIX epoch time in seconds.
        The USER is the user executing the command.
        The JOBUSER is the user that the job will run as.
        The JOBGROUP is the group that the job will run as.
        The block attribute may be used to specify that this be a blocking call, in which case this invocation will not return until the job has finished. Please note that this could be a very long time, so use this option with care.
        The ENVIRONMENT is the user's environment (key=val list separated by newlines)
        The CMDFILE is the raw stanzas of the job CMD file. These are documented elsewhere and prolly should become XML themselves.

    11. releasejob

    Release a held or deferred job. The job is put back into the scheduling queue.

    11.1 Send

            <mauicomm version="0.5" comm="releasejob" ts="[TIMESTAMP]">
            <releasejob user="[USER]" jobid="[JOBID]"/>
            </mauicomm>

        The TIMESTAMP is the UNIX epoch time in seconds.
        The USER is the user executing the command.
        The JOBID is the job to release.

    12. setres

    Set a sys reservation.

    12.1 Send

            <mauicomm version="0.5" comm="setres" ts="[TIMESTAMP]">
            <setres user="[USER]">
            <resfile><![CDATA[
            [RESSTANZAS]
            ]]></resfile>
            </setres>
            </mauicomm>

        The TIMESTAMP is the UNIX epoch time in seconds.
        The USER is the user executing the command.
        The RESSTANZAS is key=val stanzas for the reservation. These are currently undocumented and prolly should become XML themselves.

    13. showq

    Show the state of the queue and other job and reservation data structures.

    13.1 Send

            <mauicomm version="0.5" comm="showq" ts="[TIMESTAMP]">
            <showq user="[USER]" flags="[FLAGS]"/>
            </mauicomm>

        The TIMESTAMP is the UNIX epoch time in seconds.
        The USER is the user executing the command.
        The FLAGS is any combination of the following argument flags:
            r - running jobs
            i - idle jobs
            h - held jobs
            d - deferred jobs
            o - other (sys reservations)