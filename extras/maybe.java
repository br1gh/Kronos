// Experiments for Main


Job j = new Job(1, "echo test >> ${HOME}/.config/kronos/test",
                null, null, null, 30);
JobService.insert(j);

for ( Job j : JobService.getAll() ) {
    j.display();
}

for ( Execution e : ExecutionService.getAll() ) {
    e.display();
}

// "tick" every minute
// creates new executor and checks if any job can run
while ( true ) {
    Executor executor = new Executor();
    executor.doExec();

    System.out.println("Sleeping until next tick ...");
    try {
        Thread.sleep(60000);
    }
    catch ( Exception e ) {
        System.out.println(e.getMessage());
        System.exit(1);
    }
}

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
String d = LocalDateTime.now().format(formatter);
//String d = "0001";
System.out.println(d);

ExecutionService.insert(new Execution(0, 1,
                                      d, 0, ""));


for ( Job j : JobService.getAll() ) {
    j.display();
}

for ( Execution e : ExecutionService.getAll() ) {
    e.display();
}

Executor.jobExec(JobService.getAll().get(0));
