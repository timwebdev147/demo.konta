## [6.0.31] (2022-09-15)

#### Fixed

* Invoice: fixed a bug where, when generating an invoice, the default company bank details was used instead of the one selected by the user.
* Partner: fixed a regression where the partner displayed name was first name followed by name, it is now back to name followed by first name.
* Move: fixed an issue were validating a large amount of moves would lead to a technical error stopping the process.

## [6.0.30] (2022-09-01)

#### Fixed

* Ticket: fix ticket copy by resetting correctly fields that are not configured by the user.
* Invoice: fixed an issue where invoice printing PDF file had a blank page at the end of the report.
* Invoice: fixed discount display issue in the printing.
* Fixed Asset: fixed error (JNPE) when we removed first depreciation date or acquisition date.
* Move: fixed an issue where it was not possible to reverse a move linked to a notified invoice in an accounted subrogation release.

## [6.0.29] (2022-08-11)

#### Fixed

* PrintTemplate & PrintTemplateLine: several UI improvments

  - Illustrative sentence added to the top of the line template to explain the different possibilities.
  - The help explanations of the 'content' 'conditions' & 'title' text areas have been modified.
  - A new text area 'Notes' has been added to the line template to help distinguish lines the grid.
  - The column 'conditions' is now displayed in the grid.

* MRP: reset date fields when copying an existing MRP.
* Lunch voucher: fix lunch voucher computation: half-day leaves now correctly reduce number of lunch vouchers by one.
* Accounting: fixed the "Revenues vs Expenses" chart.
* Manufacturing Order: fixed an issue preventing to finish a manufacturing order with a operation order on standby.
* Expense: fixed an issue where expense ventilation did not work after setting accounting analytic template to expense lines.
* Account clearance: Fixed errors when fetching excess payments preventing the process from working correctly.

## [6.0.28] (2022-08-01)

#### Fixed

* Advanced export: fixed an error preventing partners export when trying to use the feature from partner grid view.

## [6.0.27] (2022-07-29)

#### Fixed

* MAIL TEMPLATE ASSOCIATION: fix template keys issues when using custom template for mail notifications on comments

In the custom mail template used for mail notifications sent when commenting on a form, following changes were made:
- Fix variable `$ccRecipients$` so it is filled with all followers.
- Add variable `$toRecipient$` which is currently the same as `$ccRecipients$`.
- Add variable `$commentCreator$` to only get the author of the comment.

* Stock correction: it is now possible to apply a correction on any product even if the product is not available in the stock location.
* MRP: status of the mrp is now set to draft on copy.
* MRP demo data: in MRP configuration, changed the statuses to correctly take into account ongoing manufacturing orders for the MRP computation.
* Manufacturing Order: pre-filling operations does not fill start and end date anymore, allowing them to be filled during the planification.
* Invoice: fixed an issue preventing the user to fill the type of operation when creating a new invoice with customer/supplier info not set by default.
* Message email: when sending an email, the 'To' field will now be filled with the fullname and email address instead of the name and the email address.
* Production: fixed sequence data-init.
* Contacts: checking duplicate and opening a contact form will not open a unusable form anymore.
* Configurator creator: fixed a bug during import causing some fields to have an incorrect value.
* Batches: Fixed "created on" value, before it was always set on midnight.
* Stock location: fix field title for stock computation config

Change 'Don't take in consideration for the stock calcul' 
to 'Don't take in consideration for the stock computation'

* Contract: fix typo in french translation in an error message (when closing contract).

## [6.0.26] (2022-07-07)

#### Fixed

* Sale/Purchase order: fixed an issue where a popup error was displayed to the user when creating a new order.
* Invoice: fixed an issue where duplicating an invoice without invoice line showed an error.
* Accounting situation: fixed an issue where PFP validator user was displayed when changing to a company with the PFP feature disabled.
* Journal: fixed the display of an error popup when saving a journal without journal type.
* Manuf order: fixed an error popup displayed when opening the user reporting dashboard.
* Analytic move line: Fixed a issue where we could not save a analytic move line from sale order line.
* Sale order line: hide delivered quantity field if parent is in draft or finalized.
* Check Duplicate: fixed an error occurring when using the the "check duplicate" function

Objects where you could check the duplicate depending on the chosen fields (e.g. Contacts, Products...)
now correctly opens a window with the duplicated entries instead of an error message.

* Project: Creating a new customer contract from a project now correctly fills the partner.
* Purchase request: remove the filter on selected supplier catalog product.
* Fixed asset: improve UI by preventing hidden field to be displayed during form load.
* Subrogation release: reversing an account move generated by a notification will set the subrogation release back to the status accounted.
* Birt Template: fix wrong type for id parameters in demo data.

## [6.0.25] (2022-06-23)

#### Fixed

* Reimbursement: fixed bank details and move line filter by filtering by partner.
* Menu builder: fixed menu builder copy.
* Payment mode: hide the bank order panel on a payment mode when it cannot generate pending payments.
* Weighted average price in product company is now correctly computed in company currency instead of purchase currency.
* Bank Reconciliation: fixed duplicated other bank statements during the loading process.
* Purchase order line: fixed NPE when trying to select supplier in supplier request tab.
* Sale order line: prevent user from modifying delivery quantity manually.
* Payment voucher: Manage multi banks for receipt.
* Advanced export: fixed export without archived and with ids selected.
* Translation: fix typo in "Manual" french translation.
* Expense: corrected wrong bank details on bank order generated from expense.
* Move: fixed technical error being displayed when trying to delete a single move.
* Invoice line: corrected JNPE error on updating account.
* Product: fixed an issue where an error message was displayed when emptying product pull of date.
* Stock config: when the parameter `displayLineDetailsOnPrinting` is turned off, quantities are now correctly aggregated by product.

## [6.0.24] (2022-06-10)

#### Changes

* Sale order email template: added unit in sale order line details.

#### Fixed

* Contract batch: invoicing a batch of contracts does not take into account closed contract anymore.
* Sale order/Invoice printings: fix "Dr." civility that was displayed as "Mlle.".
* Move: fix partner not being filtered according to payment mode compatible types.
* Expense line: Analytic distribution date is now set to expense line's expense date if it is setted. If not, it is still by default today date.
* MRP: fixed an issue where quantity conversion for mrp lines from manuf orders was not applied.
* Supplier: allow to create an accounting situation with a company that does not have PFP feature activated.
* Invoice: fixed an issue where printing an invoice from the form view would give an error.
* Cost sheet: fix wrong total when computing cost from bill of materials or manufacturing orders.
* Supplier: fixed an issue where realizing a reversed stock move would update supplier quality rating.
* Purchase order: fix wrong total W.T. computation when generated from purchase request.
* Bank details: fix missing fields when created from company.
* Stock rules: fix user and team fields not displayed with alert activated.
* Expense line / Move line: Analytic distribution template is now correctly copied in move line when it is created from expense.
* Stock move: fix wrong computation on supplier quality rating

When realizing a stock move, stock move lines with the conformity not filled were counting as non-compliant
and decreasing the supplier's quality rating.


## [6.0.23] (2022-05-27)

#### Fixed

* Inventory: Fix error popup on creating a new inventory line with a new company.
* Sale order line: fixed an issue where analytic distribution validation failed because of a title line generated by the pack feature.
* Product: now correctly hide the config allowing to define shipping cost per supplier in product form if we disabled the supplier catalog.
* Invoice: fix AxelorException error when emptying the operation type on the invoice form view.
* Journal: reset the list of valid accounts on company change.
* MRP: grid view from MRP menu will not also display MPS anymore.
* Manufacturing: outsourcing management

Production process lines can now be managed without outsourcing even if the parent prod process is configured as outsourced.
When created, operation orders now are correctly configured as outsourced depending on their related production process lines.

* Invoice: fix invoice grid view to have only the customer or supplier lines according to the present filter.
* Product: fix typos in fr translations and fr help.
* Invoice: fixed an issue preventing people from ventilating completed sale order.
* Accounting: hide the menu Analytic if analytic accounting management is disabled.
* Employee: When creating an employee, the step 'user creation' is now hidden if employee is already associated with a user.
* Expense: move date will now be updated if we only fill kilometric expense lines.
* Move template wizard form: fixed data input wizard popup form view.
* Notification: correctly manage refunds in payment notification process.
* Payment voucher: fix print button display and fix truncated report.
* Sale order: Duplicating a quotation will not copy the opportunity anymore.

## [6.0.22] (2022-05-11)

#### Fixed

* Lead: Fixed a bug where converting a lead without picture resulted in `NullPointerException` error and would not convert the lead.
* Inventory: Fix an error when importing inventory demo data.
* Partner: Fix an issue where partner full name was not correctly computed.
* Leave Request: Leave quantity is now by default 0 if there is no leave reason configured.
* Leave Line: fixed a bug where "days to validate" in leave line could have a wrong value.
* Stock move: Fix a bug where switching to the next stock move from unsaved record could result in a unsaved record exception.
* Manufacturing Order: Fix an issue where updating consumed/produced products panel shows an error and does not update the stock.
* Manufacturing order: Fix french translation issue for available status (is now correctly translated to 'Disponibilité').
* Manufacturing order: Display correct columns on components/manufactured product/waste stock move list grid view.
* Operation order: Fix stock move and consumed product panels display.
* Project: Fix an issue were it was not possible to generate multiple planning lines.
* Project: Fix an issue were timesheet lines generated from planning lines were not correctly marked as "to invoice".
* Stock correction: Fix an issue when modifying a quantity in stock would change the average price of the product in stock location.
* Bank Reconciliation: Translate bank reconciliation report filename.
* Stock Location: Fix `NullPointerException` error on birt report run from an external stock location (excel).
* Accounting Reports: Disable page break interval to get only one tab on excel files.
* Outsourcing: improve error message when a virtual stock location is present but not usable for outsourcing.
* Configurator: Fixed import popup title.
* Configurator Prod Process: Fix an issue preventing the user to configure a stock location from a formula.

## [6.0.21] (2022-04-29)

#### Changes

* Databackup: Add option to use fake data when making the backup with anonymization enabled.

#### Fixed

* Purchase Order: generating purchase orders now correctly applies the company purchase config for purchase printing.
* Lunch voucher: fixed issue where half days were not accounted in lunch voucher computation.
* Partner: fixed issue where missing sales config is blocking supplier save.
* INVOICE: Fix ClassNotFoundException on partner change when cash-management module is not loaded.
* Lead, Contract, Sale Order, Purchase Request, Purchase Order: add a server side check on every status change, preventing user mistake in case of malfunctioning views.
* Product: average price (WAP) is now correctly computed in product purchase currency.
* Sequence: When loading app for the first time, correctly initialize default sequences.

## [6.0.20] (2022-04-15)

#### Changes

* Add an anonymization feature to the data backup feature.

Add an anonymization option to allow the person making the data backup to get a backup with the selected fields anonymized.
For the moment fields are anonymized using an hash function, a call to an API to get fake data is not implemented yet.

#### Fixed

* BankReconciliation: On company change, empty fields related to company in bank reconciliation form view.
* Account Config: Remove account clearance editor view and instead use a standard panel.
* Leave: filter out leave reasons that do not manage accumulation.
* PaymentAssistantReport: fix empty columns when ticket is printed.
* Product: fix products full name translation on cards view.
* Stock and MRP: Add a server side check on every status change, preventing user mistake in case of malfunctioning views.
* StockLocation: fix print button on stock location grid view.

## [6.0.19] (2022-04-01)

#### Fixed

* Production process: Fix french help message in data-init.
* Product: fix filter on cost type selection.
* Product Disponibility: add filter to remove products that are not stock managed from product set in product disponibility form.
* BUDGET: Clear budget when purchase order is cancelled.
* Lead: Add missing checks for duplicated reference during lead conversion.
* Lead: Add missing checks for duplicated reference during lead conversion.
* Lead: fix 'no transaction is in progress' error when trying to convert a lead.
* Bank Reconciliation Line: Fix errors on account or a partner in a bank reconciliation line.
* Manuf Order: fix an issue where updating the quantity on generated manufacturing orders did not correctly update prod product list.
* Inventory: Filling inventory line list will now correctly exclude the lines without stock if exclude out of stock option is true.
* Docusign: remove extra status selections from Envelop status.
* Sale Order: Fix transaction issue when generating production order from sale order.
* Purchase Order: when generating a purchase order from a sale order, generated purchase order lines order is now correctly retrieved from sale order lines.
* Exception: fix message display when an error occurs on save.

For example, the message 'Invoice type missing on invoice' is now correctly displayed instead of 'com.axelor.exception.AxelorException: Invoice type missing on invoice'

* Partner category: fix french translation.
* Move, Accounting Period: improve format of displayed dates in error message.
* Supplier stock move invoicing wizard now correctly opens invoice supplier grid.
* Invoice: Remove duplicated specific notes copied from tax when we have multiple lines.
* MetaSchedule: Fix the title for 'Service' field.
* Contact: fixed duplicate contact name warning not displayed when the contact was not saved.
* Analytic distribution required on sale order line: Fix french translation.
* Analytic distribution required on purchase order line: fix french translation.
* Sale order: when generating a purchase order from a sale order, we now also generate title lines.
* User: Fix missing "create partner" and "create employee" buttons when creating a new user.
* Custom state accounting report: Fix typo in french demo data (explotation -> exploitation).
* YEAR: changed demo data for period on civil year.
* Object data config: Fix the title of the popup appearing when anonymizing data.

## [6.0.18] (2022-01-28)

#### Fixed

* GEONAMES: fix NPE while importing cities from geonames.
* Stock move: add server side check on status before realizing a stock move.
* Sequence: fixed issue where the sequence count would increment after an exception cancelling the process.
* HR(Timesheet/LeaveRequest/Expense): fix employee selection by correctly filtering out employees with closed employment contract.
* Budget: Fix budget lines update on cancelling a purchase order.
* Studio: Fix widget attrs width default on custom field

Removed default value of width from widget attrs of custom field, when created from studio.

* STOCK: Fix filters of stock forecast menu entries.
* Stock reservation: fix german translation of error message.
* INVENTORY: change export filename format to `Inventory_<sequence>_<date>.csv` (for example: `Inventory_INV1220002_20211231.csv`).
* Sequence: fix data init by adding missing full name and activating yearly reset configuration on some sequences.
* Invoice: on ventilation, now refund automatically pays the supplier invoice. This was previously only working with customer invoice.
* PrintingSettings: remove company field from PrintingSettings and remove company condition from all permissions on PrintingSettings.
* Event: fix NPE when updating start date on an unsaved event.

## [6.0.17] (2021-12-16)

#### Fixed

* STOCKMOVE/INVOICING: Correctly apply unit conversion when updating invoiced quantity in stock move lines.
* Move: fix wrong currency amount and rate computation in VAT move line.
* SALE ORDER: Blocking invoice generation when the full amount of the order has already been invoiced.
* SaleOrderLine: Fix invoiced quantities computation when having refunds.
* PERIODS: Removed overlapping periods in demo data.
* STOCK DETAILS: Fixed issue where stock locations were sometimes not properly filtered.
* Expense Line: Prevent negative and inconsistent amounts in Expense lines.
* Budget: Fix budget lines update on cancelling an invoice.

## [6.0.16] (2021-11-17)

#### Changes

* ACCOUNTING SITUATION: Add filters on customer account and supplier account.

#### Fixed

* ACCOUNT MOVE: fix copy feature by resetting more fields during copy.
* Advanced Export: add includeArchivedRecords boolean to manage archived records.
* Lead: remove useless action called on lead creation causing issues with permission.
* Account management: Add missing form and grid view to the field analytic distribution template.
* Forecast Recap: fix display of bank details last update balance date in form view.
* Invoice: fix error happening during the creation of a new invoice after generating an invoice from a purchase order.
* BANK PAYMENT BATCH: fix java.lang.NoSuchMethodException error when trying to run the batch manually.
* BatchLeaveManagement: Leave lines created by batch will now correctly have their name set.
* LeaveLine: Fixed demo data where leave lines did not have a name.
* AbstractBatch: Fixed duration which was computed in minutes instead of seconds.
* PRODUCT and PURCHASEORDERLINE: fix currency conversion when setting last purchase price as product cost price.
* Sale Order: correctly hide availability request label when stock module is not included.
* Product: fix stock details never showing any stock location line.
* Human resources: restored chart view for leave per employee.
* Accounting Move: fix NPE when we click on delete move without any moves selected.
* PRINTING SETTINGS: Fix display error when pdfHeader is not filled.
* Fix french translation "Personnalisé" to "Personnaliser".
* PRODUCTION/STOCK: Add missing / fix wrong translations.
* INTERCO: Fix analytical distribution model of generated interco invoice.
* Product Category: change wrong grid view of parentProductCategory.

## [6.0.15] (2021-10-14)

#### Fixed

* MRP: In MRP computation, the procurement method selection configured per company in the product is now chosen instead of always selecting the value in the base product.
* Extra Hours: extra hours quantity can no longer be negative on the view.
* Contract: display "supposed end date" field in default grid view.
* Invoice: fix estimated payment date computation.
* Invoice: add default bank details from partner when the invoice is generated.
* Extra hours: fix typo in french translation.

## [6.0.14] (2021-10-01)

#### Fixed

* Contract Version: Fix NPE on contract version when we push a new version to waiting status and to ongoing status.
* Quotation template: Resolved NPE while generating quotation from template that had title lines.
* FixedAsset: add filter on company on fixed asset category.
* INVOICE LINE: fixed issue where "Filter on supplier" option was set to false on invoice line modification.
* IMPORT CONFIGURATION: reset fields on copy.
* PURCHASEORDER: initialize correctly receipt state on generating stock move.
* Contract line: linked generated invoice line to contract line.
* PRODUCT: Fix barcode display for Code_39 and code_128.
* Invoice: Fix missing file type on printedPdf metafile field.
* TimesheetLine: Fix issue in working hours computation causing rounding errors.
* ManufOrder: Reset Operation orders on Production process change.
* Printings: fix printing issues when using an external birt runtime.
* Invoice: display proper error on trying to do a payment with a wrong accounting configuration.
* Print/PrintTemplate: Fix spelling for selection of footer font color.

## [6.0.13] (2021-07-28)

#### Fixed

* JOURNAL: Changed title of descriptionIdentificationOk field to 'Add accounting document N°' (English only).
* Lead: Make validate button readonly when name is empty.
* Irrecoverable: fix shifting to irrecoverable an invoice with exonerated vat raising an exception.
* Sale order invoicing wizard: it is now possible to select 'invoice all' option after creating an advance payment.
* Accounting Report: Grid view for accounting report is now sorted by newest.
* Supplier Quote: fix total price calculation for the supplier quote.
* Invoice line: fix budget display in advance search.
* Sale order line: Set product's analytic and supply informations.
* Move: fix rounding issue display on totals during manual creation of a move line.
* Fix wrong translation in english.
* Fix some french translations.
* Sequence: fix an issue where a change in sequence configuration was not correctly taken into account without restarting the application.
* Invoice: prevent the creation of a refund advance payment invoice.

## [6.0.12] (2021-07-08)

#### Features

* STOCK LOCATION: Stock valuation by purchase value.

#### Fixed

* Exception: improve exception management when an issue occurs on save.
* French translation: Translate all occurences of 'Expense' to 'Note de frais' in french.
* ROUNDING MODE: In computations, replaced half even rounding mode by half up rounding mode to prevent rounding inconsistencies.
* Product: Add unicity on serial number.
* PURCHASE ORDER: fix an issue where purchase order line list was empty when accessing it from Historical menu entry.
* Account management form: Fixed issues where Tax/Product/Product Family/Payment mode were displayed and not filled.
* Stocks: Fixed an issue where dashboards 'Upcoming supplier arrivals' and 'Late supplier arrivals' would either be empty or displaying unrelevant data.
* ManufOrder: Fix an issue were generate waste butten was not clickable.
* Mrp: fix MRP process being stuck in a loop with wrong mrp line type configuration.
* DEMO DATA: Add permission and menus for purchase user.
* InvoicePayment: Fix NPE on payment cancel.
* Product family: fixed product field and type display in account management form.

## [6.0.11] (2021-06-02)

#### Fixed

* ManufOrder: make linked sale order sequence appears a origin in printing.
* Move reversion: Fill reversal date in analytical moveline on reversed move.
* Inventory Line: Update gap, gap value and real value while importing demo data.
* Year: fix not being able to adjust fiscal year when fiscal year is closed and make sidebar readonly for new records.
* Year: fix reported balance date becoming empty after clicking 'close year' button.
* Configurator creator: fix import error on Windows.
* Sale order line: Do not check for multiple quantities when the configuration is disabled.
* StockLocation: content lines and detail lines are no longer duplicated on copy.
* ProductCategory: remove wrongly added field `dtype`.
* TrackingNumberConfig: Fix sequence being required even if we do not generate automatically a tracking number for purchase.
* Email sending: fix NPE when sending email from scheduler.
* SaleOrder: fix button to print invoices from invoicing dashlet.
* Fixed asset: fix fixed asset linear and degressive computation.
* Project: show contract panel tab only on a business project.
* CostSheetLine: display the correct icon in the tree view's dropdown list.
* Move line: remove the possibility to select a date for a move line out of the move period.
* Translation: Add missing french translations and fix incorrect existing ones in all modules.
* Configurator: fix issue preventing generation of product or sale order line when trying to fill a date field with the configurator.
* Stock correction: make stock correction reason field required.
* GeoNames Import: Improve import process, avoid importing duplicated elements.

## [6.0.10] (2021-04-29)

#### Changes

* PrintTemplate: add fields on print template.
  * Add new field to display the text on multiples columns.
  * Add new field to display a docusign signature.

#### Fixed

* SaleOrder: fix NPE on product selection when the current user does not have an active company.
* Mail message: The fields to/bcc/cc recipients in email templates are now correctly used in generated emails.
* Contract: add missing translations.
* Demo data: fix XML configuration to import partner.
* Invoice Line: fix product description not retrieved for sale invoices.
* Invoice: fix rounding error on advance payment imputation.
* Bank details: fix BBAN check on other countries than France.
* User: Do not block by default users created from external authentication.
* AppDocuSign: Fix error on trying to access the app configuration.
* Product company: add database constraint to prevent having multiple lines in a product with the same company.
* Forecast recap line type: fix data-init.
* Action builder: Fix issue where the user was unable to save.
* Invoice Line: Fix filter on supplier in supplier invoice.
* Purchase order line: fill supplier code and name on generated purchase order lines.
* Fix errors in CSV files that might cause parsing issues.
* Fix missing and wrong french translations.

## [6.0.9] (2021-04-02)

#### Fixed

* INVOICE: allow to delete a draft invoice generated from business invoicing.
* Advanced import: Reset status, file and error log on copy.
* Stock move multi invoicing: fix IndexOutOfBoundsException when trying to invoice a stock move with no lines.
* Sale Order: archive stock moves that are automatically cancelled when editing a sale order.
* Move line export: fix issue when exporting lines with special char in description.
* Project DMS: Add missing french translation.
* PRODUCT: product variant generation sequence fix.
* ADVANCED IMPORT: header visibility fix.
* Invoice: fix printing when cancelling advance.
* Projected stock: Fix projected stock always displaying 'no records found'.
* MailMessage: fix sender user always being the same for all sent messages.
* Configurator creator attributes: fix issue where `onChange` field could not be emptied.
* DebtRecoveryMethodLine: we can now only select message templates that use debt recovery history.
* Purchase order: fix error due to missing parameter when generating a purchase order printing for an email.

## [6.0.8] (2021-03-17)

#### Changes

* CRM: Dashboards improvements:
  - Improve titles and translation.
  - Modify 'Average duration between lead and first opportunity' Chart so the data is shown by months.
* User: Restrict multiple employee creation for same partner in user form.
* Prod process: description list can now be sorted.
* Sale order: Remove autofill of 'Order date' during auto-generation of order.

#### Fixed

* Accounting move printing: fix issue where lines were duplicated.
* Configurator creator: fix issue where attributes from a non active configurator model are displayed in others configurators.
* Forecast recap type: fix sale order french translation to 'Commande client' instead of 'Commande'.
* Substitute pfp validator: add missing french translations.
* Purchase order: fix default payment mode when generated from sale order.
* Invoice: when generated from a purchase order, fill the project from the order to the invoice.
* Sale order line: the project label configuration now is applied for sale order lines.
* Sale order report: qty column is now displayed regardless of the line type.
* ADVANCED EXPORT: Hide 'advanced export' button when there is nothing to export.
* Configurator creator: fix on copy issues.
* Sale and purchase orders: Fix number formatting in printings.
* PurchaseOrderLine: fix NullPointerException while selecting supplier in supplier request panel.
* Expense: fix ConstraintViolationException when validating an expense.

## [6.0.7] (2021-02-25)

#### Changes

* App Mobile: add production related fields.
* Timesheet: Auto-fill Activity when using generation assistant.
* MrpLineType: Add field of application in data init.
* ACCOUNT CHART: Add 'tax authorized on move line' and 'tax required on move line' values in account demo data.
* Invoice printing: Move partner tax number under external reference.
* MANUF ORDER: Add outsourcing field on grid view.
* Team Task invoicing: initialize default invoicing quantity to 1 instead of 0.
* CRM - Partner: The partner reference is not displayed next to the partner name in the contact form view anymore.

#### Fixed

* Invoice: Fix issue where the tax number is missing when the invoice is generated from mass stock move invoicing feature.
* Job position: add missing french translations in form.
* Invoicing project: add missing french translations in form.
* ABC Analysis: Add missing translation.
* ABC Analysis: Add an alert popup for missing sequence before printing.
* Forcast Recap Form: add missing french translation.
* AppBase Config: Add missing french translation.
* AppBase Config: Add timesheet reminder batch in demo data.
* BUDGET LINE: Fix validation on 'To date' and 'From date' fields.
* MESSAGE: fix NullPointException while generating message.
* Employment contract: solve export employment contract NullPointerException issue.
* PurchaseOrderLine: Fix title line being impossible to save.
* Contract: Fix NullPointerException on clicking 'terminate' button.
* Opportunity: Use next sequence code on copy.
* MoveLine: fix exchange rate not being computed.
* Employee: add missing french translations of form and report.
* Accounting reports: fix truncated company name if the name is too long.
* Mrp line type: add missing french translation.
* Invoice: a product must be sellable/purchasable in order to be selected in a customer/supplier invoice line.
* PRINT TEMPLATE: Fix export print template.
* Job application: add missing french translations in form.
* LeaveRequest: Block the approval when a leave request is already validated.
* Import configuration: fix wrong title of import button in form.
* Product Company: fix weighted average price value when lines are auto generated.
* Vehicle: fix auto fill vehicle in vehicle service log, cost and contract popup and change french translation.
* Training: add missing french translations in form.
* Partner: fix database issue 'More than one row with given identifier was found' on partner save.

## [6.0.6] (2021-02-08)

#### Changes

* ResourceBooking: form view change.
  * Removed 'Computed automatically if left empty' tag.
  * Name is now required.
  * Added missing translation.
* FEC Import: fetch account and journal from the company and code instead of only from the code.
* Helpdesk SLA dashboards: add translation and improve filters.
* Helpdesk Ticket dashboards: improve menu, tab and dashboard titles.
* MRP: Filter out canceled or archived sale order in sale order lines selection.
* Stock Deliveries dashboard changes:
  * Display country code instead of country alpha code.
  * Change legend name and series name in 'Customer average delivery delay'.
  * Add missing translations.
  * Add Date Range feature in some dashboards.
* Company: add missing translations in company form.
* Sync Contact: Change title to Contact synchronisation.
* MANUF ORDER: add qty and unit field on grid view.
* Citizenship: change french translation of 'citizenship' menu.
* CRM: CRM Dashboards improvements.
* Departments: change departments entry menu french translation.

#### Fixed

* OPPORTUNITY: filter out lost opportunities in best open deals dashlet.
* Menu: Add french translation of 'Partner price lists'.
* StockMove: add french translation of 'Please select the stock move(s) to print'.
* Project: add missing translations in project planning user select wizard form.
* Message: update french translations.
* TeamTask: Fix timer buttons never displaying.
* Production batch: add production batch tab french translation.
* Partner: Add missing french translation for 'Customer catalog lines'.
* Stock move: fix split into 2.

 A stock move generated from split feature now correctly keeps the link to the order that generated it.

* Global tracking log: add missing translations in wizard form.
* Stock Move: fix server error in grid view when sorting by date.
* Machine: Fix NullPointerException on machine creation.
* Project DMS : Add missing translation
* TICKET: Fix SLA policy viewer.
* DATA CONFIG LINE: add missing translations.
* Stock Move Line: fix duplicate stock move lines appearing in sale order line delivery dashlet.
* Move: fix wrong form view opened on reversing a move.
* COST SHEET REPORT: Hide cost sheet group column in printings when it is disabled in configuration.
* AccountingReport: fix detailed customer balance printing being empty.
* Fix Event calendar tab name's translation.
* ADVANCE IMPORT: add missing translations in advanced import form.
* Team task: Hide 'book resource' button if resource management is not activated.
* StockRules: Filter message template configuration so we can only select stock rules template.
* Stock Move: fix split by unit duplicating stock move lines.
* Convert demo file: add missing translation in wizard form.
* Move: Fix NullPointException error while creating move for doubtful customer.
* SaleOrder: Fix excel report.
* PRODUCT CATEGORY: Add translation for tree view.
* Partner: Fix java.lang.ClassCastException error on saving partner.
* Purchase Manager Dashboard: fix accounting family not displayed in 'Pos volume by buyer by accounting family'.
* PROJECT PLANNING TIME: Add translations for editor view.
* Fix Inventory file import.
* Printing settings: add missing translations in form view.
* Add 'Project/Business (Project)', 'Job Application' and 'Job applications' french translations.
* Data Backup: update missing translations.

## [6.0.5] (2021-01-22)

#### Changes

* Update spanish translation.
* AppBase: Add configuration to use free fixer API.

Add currency conversion functionality for non paid fixer API.

* Change dashlet title from 'Bad stock locations line' to 'Products whose future quantity is less than the minimum quantity allowed'.
* TIMESHEET REPORT: Hide blocked user in user list.
* PURCHASE MENU: Moving suppliers map under the new Maps menu.
* TeamTask: Add parent task template and team task category field on task template. Improve task tree creation for project generated from project template.
* Human Resource: change Dashboard name to 'Reportings'.
* Quality Dashboard: Fix titles and remove 'Control Points' dashlet.
* HR dashboards: Update titles french translations.
* TEAM TASK CATEGORY: Change translation for form view tab.

#### Fixed

* Quality: Fix control point dashboard sql error.
* ACCOUNT REVERSE MOVE: When generating a reverse move, keep references to analytic move lines.
* User: Change the french translation of 'All permissions'.
* Cost Sheet Line: Fix rounding issue happening during computation.
* Add process for export type 'Silae' in Payroll export batch.
* Configurator Creator: prevent the creation of duplicate attribute name.
* Invoice: Set due date readonly when selected payment condition is not free.
* AppBase DATA INIT: Fix axelor-tool dependency module name.
* EVENT: Hide past date warning after record is saved.
* StockConfig: all stock locations are now filtered per their company in the form view.
* ProjectTemplate: Fix error happening when generating project with users.
* INVOICE: In printing, hide qty for title invoice lines.
* Fix english messages in App view.
* EMPLOYMENT CONTRACT SUB TYPE: Use string widget for description field.
* Stock move mass invoicing: correctly generate a refund when the price is 0.
* Cancel Reason: Add missing french translation for 'free text'.

#### Removed

* Menu: Remove purchase orders entry menu under reportings.

## [6.0.4] (2021-01-05)

#### Features

* AppBase: Add currency conversion web service using fixer api.

#### Changes

* Stock Location Line: improve error when updating a line without unit.
* INVOICING PROJECT: Add mass update for deadline date field.
* Project: Add a button to delete team task from task tree.
* Configurator: check the condition before generating sub bill of materials.
* ACCOUNTING REPORT: add new filters for analytic reports.
* USER: Allow to select partner type when generating partner linked to the user.
* ACCOUNTING REPORT: add in analytic general ledger origin and description to analytic lines.
* INVOICING PROJECT: Add total lines for logtimes, expense lines, and team tasks.
* INVOICE LINE: add changes tracking in invoice line.

#### Fixed

* Configurator: allow groovy string interpolation in scripts.
* Global tracking: fix script in demo data to avoid NPE.
* Production Config: Workshop sequence is now only managed when 'Manage workshop' configuration is enabled.
* Followers: When selecting followers in any form view, correctly filter out followers using permissions.
* TEAMTASK: Fix type default value.
* TeamTask: Fix display issue for task dead line.
* FORECAST RECAP: In the forecast recap view, the type of forecast displayed is correct now (before it was always ingoing transaction).
* Refund: on ventilation, fix the printing file name to correctly indicate a refund.
* Move Template: fix french demo data.
* Fix import issues from geonames and new field added for geonames url.
* TEAMTASK: Fix team not filled by default when the task is created from subscribed team's menu.
* MRP: Fix english typo and add missing french translation.
* MRP PRINTING: fix empty unit column.
* MRP: fix null pointer exception when running calculation on missing procurement method.
* Manuf Order: fix operation order name.

Fix issue where the operation order name starts with null when generated from a production order.
Update operation order name with manufacturing order sequence when the manufacturing order is planned.

* Stock Move Line: fix stock move line split.
* Reconcile: Display code of reconcile group.
* Leave request: manage the case of multiple leave requests for a single day.

## [6.0.3] (2020-12-03)

#### Changes

* Inventory: create tracking number on inventory lines import if it does not exist.
* Reconcile: change amount field title
* Project: ProjectVersion can now be linked with multiple projects.
* Configurator: allows to configure the quantity in the generated sale order line when we generate a product.
* Bill of Materials: create copy of components on bill of materials copy.
* LeaveRequest: Change Reason reference type to LeaveReason.
* Configurator formula: add help panel with documentation about script syntax.
* EMPLOYEE: add french translations for employee resume printing.
* DebtRecovery: Sorting grid based on date, company and partner.
* Campaign: Allow to generate targets without filling event information.
* Campaign: Add end date in form view instead of duration for event generation.
* ProdProcessLine: Make work center editable from prod process line form view.
* MRP: change titles of mrp process related menu, grid and form view.
* TeamTask: replace the list of categories by a list of tags stored in a new table for team task tags.
* Business project invoicing update batch: mark timesheet lines as needing to be invoiced if they are attached to a task with a parent that should be invoiced.
* AppProduction: Change french title for 'Manage cost sheet group'.
* Project grid view has been improved and displays now project progress and status

#### Fixed

* COST SHEET: fix the pictures on cost sheet tree view.
* PROD PROCESS: fix required condition in order to save and print record properly.
* Sequence: avoid errors when two users call a service that updates the same sequence at the same time.
* Timesheet, Expense: Fix filter after clicking "Show timesheets/expenses to be validated".
* Company: Prevent having twice the same active bank details.
* Reconcile Group: add missing translation.
* Fix opportunity in demo data missing sequence value.
* BASE APP: Showing mail template associations in a new panel.
* INVOICE: Add product name translation in printing.
* App Production: add missing translations.
* PRODUCT: fix wrong quantity display in production information "Where-used list", in product form.
* Campaign Reminder: Adding missing translation.
* MANUF ORDER: Check if BOM and ProdProcess are applicables when planning or starting a manufacturing order.
* Team Task: Cannot chose a closed project from task project view anymore.
* Stock Move: Generate line for each 'Purchase Qty by tracking' without considering boolean 'Generate new purchase auto tracking Nbr'.
* Configurator: link the generated bill of materials to the generated sale order line even if we do not generate a product.
* ACCOUNT MOVE: set origin for new move line.
- Frequency: fix the years of a frequency.
* Configurator Creator: correctly show only the name and the button on a new configurator creator form.
* INVOICE PAYMENT: fill date using the company of the linked invoice
* Leave request: Fix 'Show leaves to be validated by my subordinates' button issue.
* Demo data: do not set products cost price to 0 when importing bill of materials components.
* SaleOrder: Fill creation date using company.
* BASE APP: Adding missing translations.
* MANUF ORDER: Set manufacturing order's outsourcing from production process's outsourcing.
* INVOICE REPORT: Fixing period format.
* BASE APP: Changing company specific product fields from list to set.
* Campaign: Adding missing translation.
* Configurator: add missing special variables in script.
* INVOICE: fix "Guice configuration errors" error when clicking "update lines with selected project".
* Cost Sheet Line: fix error if the product has no prices configured and is not purchasable.
* MAIL MESSAGE: Use of custom template on mail generated by a comment post.
* Product Company: fix missing values in demo data.
* Campaign: Hide tool button if all child items are hidden.
* Product: Empty last purchase price on copy.

## [6.0.2] (2020-11-16)

#### Changes

* MRP: add error log panel.
* Stock Config: Add inventory valuation type configuration.
* Project: When generating sale order from project changed name of the generated tab from 'Sale order' to 'Sale quotation'.
* PURCHASE REQUEST: Show purchase order with its status and receipt state in follow-up panel and remove purchase orders.
* Add exclude task filter in demo data for job costing app.
* OperationOrder: Modify the machineWorkCenter field name to machine & Resolve calendar color issue.
* USER: Add boolean to display the electronic signature on quotations.
* Refund: the file name now indicates 'Refund' instead of 'Invoice' if we print a refund.
* PURCHASE REQUEST: Two new fields requester and validator added.

#### Fixed

* Invoice and Purchase Order: Set project to invoice and purchase order lines when generated from sale order.
* Stock Move Line: unit price becomes readonly if the stock move is generated from orders.
* PurchaseOrder: Fix error on requesting due to missing production module field in report.
* PROJECT: Add filter for project field in project planning time line editor.
* LEAVE REQUEST: Show validation and refusal fields only if filled.
* App: prevent user from deleting or adding an app from interface.
* Invoice: changed error message when ventilating an invoice anterior to the last ventilated invoice.
* Invoice : Date of the last ventilated invoice is now displayed
* FORECAST RECAP LINE TYPE: when the type is changed, the operation type field become empty.
* FORECAST RECAP LINE TYPE: the title Operaton Type is replaced by Operation type and its french translation has been added.
* CONVERT LEAD WIZARD FORM: Add partner information translation.
* PURCHASE REQUEST: Delete value from status select.
* Project: show task invoicing dashlet only on business projects.
* ADVANCED EXPORT: Extended selections are not exported.
* Bank Statement Lines: line color is now accurate when importing a bank statement.
* Configurator: fix issues in import/export

Add missing product family formula in demo data.
Fix imported attributes of meta json field missing updated configurator creator id.

* Inventory: Add missing fields in demo data.
* PAYMENT: Fix error message when multi bank details is enabled.
* FORECAST GENERATOR: Copying a forecast generator keeps only the values of fields filled at its creation and resets the other fields.
* Opportunity: Fix sequence on demo data.
* MRP form: remove duplicate product panel in filter.
* PURCHASE REQUEST CREATOR: Delete purchase request creator object.
* Partner: Hide the generate project button in partner contact form view.
* Inventory: Add missing translations, fix header display and add inventory sequence on each report page.
* Stock Move Line: fixed conversion issue when changing values in editable-grid and form view.
* Stock Move: fix location planned quantity not updating on some cases on real quantity change in planned stock moves.
* Prevent NPE on current user when some services were called from a scheduler.
* Analytic Move Line: Change project depending on the state of the parent Order or Invoice
* EMPLOYMENT CONTRACT: fixed EmploymentContractTemplate doesn't exist error when printing
* Manufacturing order: Display residual products in report printing only if products exist.
* ADVANCED EXPORT: Add error message when user language is null.
* AppCrm: Change french translation of the configuration to display customer description in opportunity.
* Product: fix bill of materials generated from product form view not appearing in bill of materials list.

## [6.0.1] (2020-10-22)

#### Features

* Account: Add import of FEC file exported from accounting export.

#### Changes

* Partner stock settings: add default external stock location.

Add default external stock location in partner configuration, that will be
used as a destination for sales and a from location for purchases.

* Email: do not block process when an error occurs on sending mail.

Automatic mail notification can be enabled on stock moves, invoices, and
manufacturing order. If we have an error when sending the message, the
process will now not be blocking but will still show the error to the user.

* EbicsUser: Manage fields visibilty.

In EbicsUser form view display serial number (CORP) and show required
password only if user type is signatory and ebics partner mode is ebics TS,

* UNIT: fill automatically the label.

Unit: If the label is empty then it fills automatically with the name.
* MAIL MESSAGE: add demo template for sale order update.
* TICKET: Addition of color on selection in ticket grid and form view.
* QUALITY ALERT: Addition of color on selection in quality alert grid and form view.
* Unit Conversion: make type required and hide other fields when the type is empty.
* ManufOrder: Add color to priority field.
* Period: make from date and to date fields required in form view.
* DEBT RECOVERY METHOD LINE: Debt recovery level is now required.
* In Sale order and Stock move, hide/readonly allocation related entities for product type service.
* TaxEquiv: Make taxes required in form view.
* ANALYTIC MOVE LINE: hide date, type and account type in form view opened from a invoice line.
* Accounting Year: make reported balance date required.
* ACCOUNTING MOVE: change the debit and credit field positions in total calculation form view.
* SOP: Rename categoryFamily field to productCategory.
* PaymentMode: make type and in or out select fields required in form view.
* TraceBackRepository: Remove deprecated constants.
* Invoice: set unit price value according to hide discount value for invoice report.
* LEAVELINE: change menu name 'All employees's leave lines' to 'Leave accounts'.
* ANALYTIC MOVE LINE: made some field mandatory and added tracking.
* IMPORT CONFIGURATION: add a status and process start date and end date.
* Tax: make type required in form view.
* ANALYTIC MOVE LINE: hide date, type and account type in form view opened from a sale order or puchase order line.
* Payment Condition: make type required in form view.
* Account: make account type and company required.
* Fixed Asset Category: make Degressive coef, Computation method and Number of depreciation fields required in form.
* Account Management: Make fields required in view if they are needed for the account management type.
* MoveTemplateType: type is now required.
* AnalyticMoveLine: make type required in form view.
* AccountMoveTemplate: make company field required.
* Move Template Line: Make debit, credit and percentage required in line form.
* INVOICE LINE: make type required in form view.

#### Fixed

* Fix Employees and expenses issues.
  - On kilometric log, the total distance travelled is now updated only if the expense line is added.
  - The kilometric log now has an unique constraint on employee and year.
  - Error message when missing year has been improved to display the year's type.
* SALE ORDER: Make visible some fields on sale order finished in date panel.
* ACCOUNTING BATCH: corrected conflict between boolean isTaxRequiredOnMoveLine and closure/opening accounting batch.
* Account Move: make date and currency required.
* Demo data: fix ICalendar permission that were not working.
* MRP: Stop the MRP computation if a loop in bill of materials components is happening.
* PARTNER: corrected "employee field doesn't exist" after loading a partner if human-resource module is not installed.
* Sale Order Report: fix warning appearing when launching the report.
* Remove hard-coded locale to use the correct locale from the user in some template and export generation processes.
* Fix `cannot be cast` exception on deleting some objects.
* YEAR: corrected sql error and hibernate error when closing a fiscal year.
* Copy analytic move lines when generating invoice from sale order and purchase order.
* BANK ORDER: fix NPE error during file generation.
* LogisticalFormLine: Fix stock move line domain.
* Template: Fix missing action error when marketing module is not installed.

## [6.0.0] (2020-10-05)

#### Features

* PRINT TEMPLATE: create test button to check print template line expression.
* HR: add employment contract sub type.
* PRODUCTION: created buttons in product to create new bill of material and production process.
* Axelor DocuSign: add new module axelor-docusign.
* Axelor Project DMS: add new module axelor-project-dms.
* PRINT TEMPLATE: Rework print template feature.

Add new configurations for print template: print format, sequence, columns
    number, conditions, signature

* TEMPLATE: update template engine: the user can now choose between StringTemplate or groovy.
* MANUFACTURING: Sales & Operation Planning (PIC).
* MACHINE: Implement tool management on machines.
* MAIL MESSAGE: use template object for email generated from a notification message.
* Partner: Add a new partner type 'Subcontractor' and add field related to outsourcing in manufacturing.
* PRINT TEMPLATE: Add XML export and import.
* Production: Manage MPS (Master Production Schedule) process.
* PRINT TEMPLATE: Use Itext instead of birt to generate templates.
* PRODUCTION: Add Master production scheduling charge.
* New changelog management.

#### Changes

* PRINT TEMPLATE: Add header and footer settings in print template.
* Print Template: use locale based on selected language in Template.
* PRINT TEMPLATE LINE: add new field 'ignore the line'.
* Production: machine work center is now a machine instead of a work center.
* MPS/MRP: title before sequence to change depending on the type.
* Use relative path instead of absolute path in configuration file path fields.
* Production: Remove stock location in machine type.
* Project DMS: Move 'Project DMS' menu inside projects main menu.
* MANUF ORDER: Print residual products on report and add panel of residual products.
* PURCHASE ORDER LINE: Replace the min sale price field by a field that indicates the maximum purchase price recommended.
* USER: the admin can now force the user to change password on the next connection.
* Invoice: Add tracking for most fields.

#### Fixed

* Production configuration: fix stock location filter in workshop sequence config line and make the grid editable.
* Quality Alert: Show title of fields description, corrective actions and preventive actions.
* Email message template: remove from address in template.

Setting a custom `from` address per email template is now disabled, as the from
address should depend only on the SMTP account. The `from` should now always
be set in SMTP account configuration.

* LeaveReason: rename `leaveReason` field into `name`.
* JobPosition: Remove character limit on Profile Wanted field.

[6.0.31]: https://github.com/axelor/axelor-open-suite/compare/v6.0.30...v6.0.31
[6.0.30]: https://github.com/axelor/axelor-open-suite/compare/v6.0.29...v6.0.30
[6.0.29]: https://github.com/axelor/axelor-open-suite/compare/v6.0.28...v6.0.29
[6.0.28]: https://github.com/axelor/axelor-open-suite/compare/v6.0.27...v6.0.28
[6.0.27]: https://github.com/axelor/axelor-open-suite/compare/v6.0.26...v6.0.27
[6.0.26]: https://github.com/axelor/axelor-open-suite/compare/v6.0.25...v6.0.26
[6.0.25]: https://github.com/axelor/axelor-open-suite/compare/v6.0.24...v6.0.25
[6.0.24]: https://github.com/axelor/axelor-open-suite/compare/v6.0.23...v6.0.24
[6.0.23]: https://github.com/axelor/axelor-open-suite/compare/v6.0.22...v6.0.23
[6.0.22]: https://github.com/axelor/axelor-open-suite/compare/v6.0.21...v6.0.22
[6.0.21]: https://github.com/axelor/axelor-open-suite/compare/v6.0.20...v6.0.21
[6.0.20]: https://github.com/axelor/axelor-open-suite/compare/v6.0.19...v6.0.20
[6.0.19]: https://github.com/axelor/axelor-open-suite/compare/v6.0.18...v6.0.19
[6.0.18]: https://github.com/axelor/axelor-open-suite/compare/v6.0.17...v6.0.18
[6.0.17]: https://github.com/axelor/axelor-open-suite/compare/v6.0.16...v6.0.17
[6.0.16]: https://github.com/axelor/axelor-open-suite/compare/v6.0.15...v6.0.16
[6.0.15]: https://github.com/axelor/axelor-open-suite/compare/v6.0.14...v6.0.15
[6.0.14]: https://github.com/axelor/axelor-open-suite/compare/v6.0.13...v6.0.14
[6.0.13]: https://github.com/axelor/axelor-open-suite/compare/v6.0.12...v6.0.13
[6.0.12]: https://github.com/axelor/axelor-open-suite/compare/v6.0.11...v6.0.12
[6.0.11]: https://github.com/axelor/axelor-open-suite/compare/v6.0.10...v6.0.11
[6.0.10]: https://github.com/axelor/axelor-open-suite/compare/v6.0.9...v6.0.10
[6.0.9]: https://github.com/axelor/axelor-open-suite/compare/v6.0.8...v6.0.9
[6.0.8]: https://github.com/axelor/axelor-open-suite/compare/v6.0.7...v6.0.8
[6.0.7]: https://github.com/axelor/axelor-open-suite/compare/v6.0.6...v6.0.7
[6.0.6]: https://github.com/axelor/axelor-open-suite/compare/v6.0.5...v6.0.6
[6.0.5]: https://github.com/axelor/axelor-open-suite/compare/v6.0.4...v6.0.5
[6.0.4]: https://github.com/axelor/axelor-open-suite/compare/v6.0.3...v6.0.4
[6.0.3]: https://github.com/axelor/axelor-open-suite/compare/v6.0.2...v6.0.3
[6.0.2]: https://github.com/axelor/axelor-open-suite/compare/v6.0.1...v6.0.2
[6.0.1]: https://github.com/axelor/axelor-open-suite/compare/v6.0.0...v6.0.1
[6.0.0]: https://github.com/axelor/axelor-open-suite/compare/v5.4.1...v6.0.0
