/**
 * Deletes all pngs from this folder.
 */

const fs = require("fs");
const path = require("path");

const files = fs.readdirSync(".");

const filesToDelete = files.filter((f) => f.endsWith(".png"));

filesToDelete.forEach((f) => fs.unlinkSync(path.join(".", f)));

console.log(`Deleted ${filesToDelete.length} files.`);
