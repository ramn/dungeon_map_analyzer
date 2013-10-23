# Example format
# %YAML 1.2
---
Resources:
  Egyptian Room:
    - Gold coffin
  Loud Room:
    - Platinum bar
  Dam Lobby:
    - Guidebook
    - Matchbook
  Maintenance Room:
    - Toothpaste
  Grail Room:
    - Grail

Map:
  Glacier Room:
    n: Stream view
    e: Egyptian Room
  Stream view:
    n: Glacier Room
    e: Reservoir South
  Egyptian Room:
    up: Glacier Room
    e: Rocky Crawl
    s: Volcano View
  Volcano View:
    unknown:
  Damp Cellar:
    e: Troll room
    s: West of Chasm
    w: Steep metal ramp
  Troll Room:
    n: East West Passage
    e: North South Crawlway
    w: Damp Cellar
    s: Maze # Danger! You get lost in here
  East West Passage:
    e: Round Room
    w: Troll Room
    down: Deep ravine
  Deep ravine:
    e: South of Chasm
    w: Rocky Crawl
    down: Reservoir South
