# Plan


## GUI frontend

SWING


## DB connection

JDBC

DB placed in:
- Linux: ~/.cache/kronos/db.sqlite
- Windows: %%APPDATA%%/kronos/db.sqlite
- else: in the basedir of launched program


## Examples

- `slmngr /rearm`
- show rock-face gif
- system shutdown


# Errors

- creates DB when ran from Idea, fails when run from a jar created by `mvn`.
  > No suitable driver found for jdbc:sqlite:/home/xy/.config/kronos/db.sqlite
