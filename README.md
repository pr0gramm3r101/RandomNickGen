# RandomNickGen
RandomNickGen is a program for generating random Minecraft usernames, guaranteed to not be taken.

## How it works
1. The program starts with a random word from the [database](src/main/resources/words.txt).
2. It adds random common prefixes and suffixes of Minecraft usernames like:
   - the
   - not
   - good
   - bad
   - great
   - lol
   - xd
   - error
   - 4k
   
   and more...
3. It randomly changes letters with similar-looking numbers.
4. It randomly adds underscores to the start and end.
5. It randomly capitalizes the name.
6. Then it performs checks on the name to ensure it's valid and free to use.

## How to use
Download the JAR file from [Releases](https://github.com/pr0gramm3r101/RandomNickGen/releases/latest).
If you want to use the latest development build, download from [Actions](https://github.com/pr0gramm3r101/RandomNickGen/actions)
and unzip the file.

Run the following command (applicable to Linux and macOS):

```
java -jar <file> <args>
```

- `<file>` - the downloaded JAR file (make sure to put it into the same folder you execute the command in)
- `<args>` - the arguments for the program, for full reference read the next section.

## CLI options
| Name                     | Parameter  | Description                                                                           |
|--------------------------|------------|---------------------------------------------------------------------------------------|
| `--count <int>`          | An integer | Generates multiple nicknames at a time                                                |
| `--quiet`, `-q`          |            | Doesn't output any logs                                                               |
| `--force-truncate`, `-t` |            | If the name is longer than 16 characters, it will be truncated to first 16 characters |
| `--no-existence-check`   |            | Doesn't check for existence of the name. Highly increases performance                 |
| `--no-log`               |            | Fully disable logs, no logs will be shown or written. Increases performance           |
| `--save`, `-s`           |            | Saves all generated nicknames to `.generated_nicks` in current folder                 |
