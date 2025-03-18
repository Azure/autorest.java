// @ts-check
import eslint from "@eslint/js";
import unicorn from "eslint-plugin-unicorn";
import tsEslint from "typescript-eslint";

/** Config that will apply to all files */
const allFilesConfig = tsEslint.config({
  plugins: {
    unicorn,
  },
  rules: {
    /**
     * Typescript plugin overrides
     */
    "@typescript-eslint/no-non-null-assertion": "off",
    "@typescript-eslint/no-explicit-any": "off",
    "@typescript-eslint/no-inferrable-types": "off",
    "@typescript-eslint/no-empty-function": "off",
    "@typescript-eslint/no-empty-interface": "off",
    "@typescript-eslint/no-empty-object-type": "off",
    "@typescript-eslint/no-unused-vars": [
      "warn",
      {
        varsIgnorePattern: "^_",
        argsIgnorePattern: ".*",
        ignoreRestSiblings: true,
        caughtErrorsIgnorePattern: ".*",
      },
    ],

    // This rule is bugged https://github.com/typescript-eslint/typescript-eslint/issues/6538
    "@typescript-eslint/no-misused-promises": "off",
    "@typescript-eslint/no-unused-expressions": [
      "warn",
      { allowShortCircuit: true, allowTernary: true },
    ],

    /**
     * Unicorn
     */
    "unicorn/filename-case": ["error", { case: "kebabCase" }],

    /**
     * Core
     */
    "no-inner-declarations": "off",
    "no-empty": "off",
    "no-constant-condition": "off",
    "no-case-declarations": "off",
    "no-ex-assign": "off",
    "no-undef": "off",
    "prefer-const": [
      "warn",
      {
        destructuring: "all",
      },
    ],
    eqeqeq: ["warn", "always", { null: "ignore" }],

    // Do not want console.log left from debugging or using console.log for logging. Use the program logger.
    "no-console": "warn",

    // Symbols should have a description so it can be serialized.
    "symbol-description": "warn",
  },
});

/** Config that will apply to all typescript files only
 * @param {string} root
 */
export function getTypeScriptProjectRules(root) {
  return tsEslint.config({
    files: [
      "**/src/**/*.ts",
    ],
    plugins: {},
    languageOptions: {
      parserOptions: {
        projectService: {
          allowDefaultProject: ["vitest.config.ts"],
        },
        tsconfigRootDir: root,
      },
    },
    rules: {
      // Only put rules here that need typescript project information
      "@typescript-eslint/no-floating-promises": "error",
      "@typescript-eslint/no-deprecated": "warn",
    },
  });
}

export const TypeSpecCommonEslintConfigs = [
  eslint.configs.recommended,
  ...tsEslint.configs.recommended,
  ...allFilesConfig,
];

export default tsEslint.config(
  {
    ignores: [
      "**/dist/**/*",
      "**/temp/**/*",
      "generator/**/*",
    ],
  },
  ...TypeSpecCommonEslintConfigs,
  ...getTypeScriptProjectRules(import.meta.dirname),
);
