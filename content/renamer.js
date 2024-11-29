/**
 * Renames the exported sprites from Aseprite into the correct minecraft texture names.
 * This assumes all crops in the Aseprite file are tagged and their frames in order.
 */

const fs = require("fs");
const path = require("path");
const meta = require("./seeds.json").meta;

const frameCount = meta.frameTags[meta.frameTags.length - 1].to + 1;

console.log(`Renaming ${frameCount} files.`);

// TODO: add support for more or less stages
const FILENAME_BY_INDEX = [
  "",
  "_seeds",
  "_crop_stage0",
  "_crop_stage1",
  "_crop_stage2",
  "_crop_stage3",
  "_crop_stage4",
  "_crop_stage5",
];

let seedIndex = 1;

for (let tagIndex = 0; tagIndex < meta.frameTags.length; tagIndex++) {
  for (
    let spriteTypeIndex = 0;
    spriteTypeIndex < frameCount / meta.frameTags.length;
    spriteTypeIndex++
  ) {
    const fileNameAppendix = FILENAME_BY_INDEX[spriteTypeIndex];
    fs.renameSync(
      path.join(".", `seed${seedIndex}.png`),
      path.join(meta.frameTags[tagIndex].name + fileNameAppendix + ".png")
    );
    seedIndex++;
  }
}
