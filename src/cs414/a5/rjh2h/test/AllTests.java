package cs414.a5.rjh2h.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountPaymentTest.class, ALPRTest.class,
		BillingAccountTest.class, CashPaymentTest.class, 
		CreditPaymentTest.class, DataStorageTest.class, EntryKioskTest.class,
		EntryKioskUITest.class, ExitKioskTest.class, ExitKioskUITest.class,
		GarageUITest.class, GateTest.class, LoginUITest.class,
		RegisterTest.class, RegisterUITest.class, SignTest.class,
		SignUITest.class, SysAdminUITest.class, SystemAccountTest.class,
		SystemPreferencesTest.class, TicketTest.class, TransactionTest.class,
		UsageReportsTest.class, UsageReportsUITest.class, VehicleTest.class })
public class AllTests {

}
