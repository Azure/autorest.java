import { defineConfig } from "vitest/config";

export default defineConfig({
  test: {
    environment: "node",
    isolate: false,
    coverage: {
      reporter: ["cobertura", "json", "text"],
    },
    outputFile: {
      junit: "./test-results.xml",
    },
    include: ["test/**/*.test.ts"],
    exclude: ["node_modules", "dist/test"],
  },
  server: {
    watch: {
      ignored: [],
    },
  },
});
