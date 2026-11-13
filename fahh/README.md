# Auto Dupe All (Fabric 26.2)

## What this starter project does
- Adds `/autodupe <count>` to copy the item in the player's main hand.
- `count` is 1–64 additional copies.
- Requires permission level 2 (operator / cheats enabled).
- The command runs on the logical server, so install this mod on the server as well as the client when playing multiplayer.
- In singleplayer, enable cheats / use an operator-capable world.

## Build on GitHub Actions
1. Upload the contents of this ZIP into the root of your `AutoDupeAll` GitHub repository.
2. Commit the files to the `main` branch.
3. Open the repository's **Actions** tab.
4. Select **Build AutoDupeAll** and press **Run workflow** (or let the push trigger it).
5. Open the completed run and download the `AutoDupeAll-1.26.2` artifact.
6. Extract the artifact ZIP; put the `.jar` into the `mods` folder on both client and server.

## Important
This is a starter implementation, not a tested binary. Minecraft 26.2 and its modding APIs are new; if GitHub Actions reports a compile error, share the full error log so the source can be adjusted. Back up your world before testing. The current starter does not yet include the planned K/J GUI.
